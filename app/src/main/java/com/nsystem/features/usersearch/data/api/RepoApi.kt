package com.nsystem.features.usersearch.data.api

import com.nsystem.features.usersearch.data.model.UserSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {

    @GET("search/repositories")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String,
        @Query("order") order: String,
    ): Response<UserSearch>
}