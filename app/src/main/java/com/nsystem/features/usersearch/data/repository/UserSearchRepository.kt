package com.nsystem.features.usersearch.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nsystem.core.ApplicationDatabase
import com.nsystem.features.usersearch.data.api.UserApi
import com.nsystem.features.usersearch.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

// TODO: 05/01/22 Remove after use case removed and repo repository is finished
class UserSearchRepository @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
    private val userApi: UserApi
) {

    @ExperimentalPagingApi
    fun getSearchResultStream(query: String): Flow<PagingData<User>> {
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { applicationDatabase.userDao().getUsers(dbQuery) }

        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = UserSearchRemoteMediator(
                query = query,
                userApi = userApi,
                applicationDatabase = applicationDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun getUser(username: String) = flowOf(
        applicationDatabase.userDao().getUser(username)
    )

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}