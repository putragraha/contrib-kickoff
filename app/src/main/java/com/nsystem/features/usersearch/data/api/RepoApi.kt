package com.nsystem.features.usersearch.data.api

import com.nsystem.features.usersearch.data.model.RepoOld
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoApi {

    @GET("users/{username}/repos")
    suspend fun getUserRepo(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<RepoOld>>
}