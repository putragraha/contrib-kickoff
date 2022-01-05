package com.nsystem.features.usersearch.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nsystem.core.ApplicationDatabase
import com.nsystem.features.usersearch.data.api.UserApi
import com.nsystem.features.usersearch.data.model.User
import com.nsystem.features.usersearch.data.model.UserRemoteKeys
import retrofit2.HttpException
import java.io.IOException

// TODO: 05/01/22 Remove on no longer usage
@ExperimentalPagingApi
class UserSearchRemoteMediator(
    private val query: String,
    private val userApi: UserApi,
    private val applicationDatabase: ApplicationDatabase
): RemoteMediator<Int, User>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
        }

        try {
            val userSearchApiResponse = userApi.searchUser(
                query = query,
                page = page,
                perPage = state.config.pageSize,
                sort = DEFAULT_SORT_QUERY,
                order = DEFAULT_ORDER_QUERY
            )
            val userApiResponse = userSearchApiResponse.takeIf { it.isSuccessful }
                ?.body()
                ?.items
                ?.map { userApi.getUser(it.login) }

            if (userApiResponse?.all { it.isSuccessful } == true) {
                val users = userApiResponse.map { it.body() }
                val userSearchResponse = userSearchApiResponse.body()
                val endOfPaginationReached = userSearchResponse?.items?.isEmpty() == true
                applicationDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        applicationDatabase.userRemoteKeysDaoDao().clearUserRemoteKeys()
                        applicationDatabase.userDao().clearUsers()
                    }
                    val prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    val keys = userSearchResponse?.items?.map {
                        UserRemoteKeys(login = it.login, prevKey = prevKey, nextKey = nextKey)
                    }
                    keys?.let {
                        applicationDatabase.userRemoteKeysDaoDao().insertAll(keys)
                        applicationDatabase.userDao().insertAll(users)
                    }
                }
                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                val errorCode = userApiResponse?.firstOrNull { it.isSuccessful }
                    ?.code()
                    ?.toString()
                    ?: DEFAULT_API_ERROR

                return MediatorResult.Error(Throwable(errorCode))
            }
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, User>
    ) = state.anchorPosition?.let { position ->
        state.closestItemToPosition(position)?.login?.let { login ->
            applicationDatabase.userRemoteKeysDaoDao().getUserRemoteKeys(login)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, User>): UserRemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { applicationDatabase.userRemoteKeysDaoDao().getUserRemoteKeys(it.login) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, User>): UserRemoteKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data
            ?.firstOrNull()
            ?.let { applicationDatabase.userRemoteKeysDaoDao().getUserRemoteKeys(it.login) }
    }

    companion object {

        private const val INITIAL_PAGE_INDEX = 1

        private const val DEFAULT_SORT_QUERY = "followers"

        private const val DEFAULT_ORDER_QUERY = "desc"

        private const val DEFAULT_API_ERROR = "Request Unsuccessful"
    }
}