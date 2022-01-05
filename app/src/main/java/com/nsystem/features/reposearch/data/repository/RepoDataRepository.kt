package com.nsystem.features.reposearch.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nsystem.core.ApplicationDatabase
import com.nsystem.features.reposearch.data.api.DEFAULT_PAGE_SIZE
import com.nsystem.features.reposearch.data.api.RepoApi
import com.nsystem.features.reposearch.data.model.RepoEntity
import com.nsystem.features.reposearch.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoDataRepository @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
    private val repoApi: RepoApi
): RepoRepository<RepoEntity> {

    override suspend fun searchRepo(query: String): Flow<PagingData<RepoEntity>> {
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { applicationDatabase.repoDao().searchRepo(dbQuery) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            remoteMediator = RepoRemoteMediator(
                query = query,
                repoApi = repoApi,
                applicationDatabase = applicationDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}