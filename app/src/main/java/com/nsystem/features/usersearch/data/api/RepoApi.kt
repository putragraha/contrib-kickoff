package com.nsystem.features.usersearch.data.api

import com.nsystem.features.usersearch.data.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

const val DEFAULT_TAG = "help-wanted-issues"

const val INITIAL_PAGE_INDEX = 1

const val DEFAULT_PAGE_SIZE = 10

interface RepoApi {

    @GET("search/repositories")
    suspend fun searchRepo(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String
    ): RepoSearchResponse
}