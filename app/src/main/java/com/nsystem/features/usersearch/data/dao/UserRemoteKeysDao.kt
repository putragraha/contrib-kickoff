package com.nsystem.features.usersearch.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsystem.features.usersearch.data.model.UserRemoteKeysEntity

@Dao
interface UserRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userRemoteKeys: List<UserRemoteKeysEntity>)

    @Query("SELECT * FROM user_remote_keys WHERE username = :username")
    suspend fun getUserRemoteKeys(username: String): UserRemoteKeysEntity?

    @Query("DELETE FROM user_remote_keys")
    suspend fun clearUserRemoteKeys()

}