package com.nsystem.features.usersearch.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsystem.features.usersearch.data.model.User

// TODO: 04/01/22 Remove after application database no longer use these
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<User?>)

    @Query("SELECT * FROM user WHERE " +
            "login LIKE :queryString OR " +
            "name LIKE :queryString OR " +
            "company LIKE :queryString OR " +
            "blog LIKE :queryString OR " +
            "location LIKE :queryString OR " +
            "email LIKE :queryString OR " +
            "bio LIKE :queryString OR " +
            "twitter_username LIKE :queryString " +
            "ORDER BY followers DESC")
    fun getUsers(queryString: String): PagingSource<Int, User>

    @Query("SELECT * FROM user WHERE login=:username")
    suspend fun getUser(username: String): User

    @Query("DELETE FROM user")
    suspend fun clearUsers()
}