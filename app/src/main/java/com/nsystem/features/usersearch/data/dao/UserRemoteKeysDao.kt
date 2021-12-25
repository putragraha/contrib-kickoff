package com.nsystem.features.usersearch.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsystem.features.usersearch.data.model.UserRemoteKeys

@Dao
interface UserRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userRemoteKeys: List<UserRemoteKeys>)

    @Query("SELECT * FROM user_remote_keys WHERE login = :login")
    suspend fun getUserRemoteKeys(login: String): UserRemoteKeys?

    @Query("DELETE FROM user_remote_keys")
    suspend fun clearUserRemoteKeys()

}