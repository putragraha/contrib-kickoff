package com.nsystem.features.usersearch.data.repository

import com.nsystem.features.usersearch.data.api.RepoApi
import com.nsystem.features.usersearch.data.model.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val repoApi: RepoApi
) {

    suspend fun getUserRepo(username: String): Flow<List<Repo>> {
        val apiResponse = repoApi.getUserRepo(username, INITIAL_PAGE_INDEX, DEFAULT_PAGE_SIZE)
        var repos = emptyList<Repo>()

        if (apiResponse.isSuccessful) {
            repos = apiResponse.body() ?: emptyList()
        }

        return flowOf(repos)
    }

    companion object {

        private const val INITIAL_PAGE_INDEX = 1

        private const val DEFAULT_PAGE_SIZE = 10
    }
}