package com.nsystem.features.usersearch.data.api

import com.nsystem.features.usersearch.data.model.UserSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UserApi {

    @GET("search/users")
    fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String,
        @Query("order") order: String,
    ): Response<UserSearchResponse>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Response<UserSearchResponse>
}