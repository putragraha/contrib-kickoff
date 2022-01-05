package com.nsystem.features.usersearch.data.api

import com.nsystem.features.usersearch.data.model.User
import com.nsystem.features.usersearch.data.model.UserSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// TODO: 05/01/22 Remove on no longer usage
interface UserApi {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String,
        @Query("order") order: String,
    ): Response<UserSearch>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<User>
}