package com.nsystem.features.usersearch.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nsystem.core.ApplicationDatabase
import com.nsystem.features.usersearch.data.api.*
import com.nsystem.features.usersearch.data.mapper.toRepoEntities
import com.nsystem.features.usersearch.data.mapper.toRepoRemoteKeysEntities
import com.nsystem.features.usersearch.data.model.RepoEntity
import com.nsystem.features.usersearch.data.model.RepoRemoteKeysEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RepoRemoteMediator(
    private val query: String,
    private val repoApi: RepoApi,
    private val applicationDatabase: ApplicationDatabase
): RemoteMediator<Int, RepoEntity>() {

    override suspend fun initialize() = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoEntity>
    ): MediatorResult {
        val currentRepoRemoteKeysEntity = getCurrentRepoRemoteKeysEntity(loadType, state)
        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                currentRepoRemoteKeysEntity?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                currentRepoRemoteKeysEntity?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = currentRepoRemoteKeysEntity != null
                )
            }
            LoadType.APPEND -> {
                currentRepoRemoteKeysEntity?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = currentRepoRemoteKeysEntity != null
                )
            }
        }
        val apiQuery = query + IN_QUALIFIER

        try {
            val repoResponse =
                repoApi.searchRepo(apiQuery, currentPage, state.config.pageSize, DEFAULT_TAG)
            val repos = repoResponse.items
            val endOfPaginationReached = repos.isEmpty()
            val startOfPagination = currentPage == INITIAL_PAGE_INDEX
            applicationDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    applicationDatabase.repoRemoteKeysDao().clearRepoRemoteKeys()
                    applicationDatabase.repoDao().clearRepo()
                }

                val prevKey = if (startOfPagination) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1
                val repoRemoteKeys = repos.toRepoRemoteKeysEntities(prevKey, nextKey)
                applicationDatabase.repoDao().insertAll(repos.toRepoEntities())
                applicationDatabase.repoRemoteKeysDao().insertAll(repoRemoteKeys)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getCurrentRepoRemoteKeysEntity(
        loadType: LoadType,
        state: PagingState<Int, RepoEntity>
    ): RepoRemoteKeysEntity? {
        val currentRepoEntity = when (loadType) {
            LoadType.REFRESH -> state.anchorPosition?.let { state.closestItemToPosition(it) }
            LoadType.PREPEND -> {
                state.pages
                    .firstOrNull { it.data.isNotEmpty() }
                    ?.data
                    ?.firstOrNull()
            }
            LoadType.APPEND -> {
                state.pages
                    .lastOrNull { it.data.isNotEmpty() }
                    ?.data
                    ?.lastOrNull()
            }
        }

        return currentRepoEntity?.id?.let { repoId ->
            applicationDatabase.repoRemoteKeysDao().getRepoRemoteKeys(repoId)
        }
    }
}