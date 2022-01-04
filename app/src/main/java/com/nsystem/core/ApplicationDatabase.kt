package com.nsystem.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nsystem.features.usersearch.data.dao.RepoDao
import com.nsystem.features.usersearch.data.dao.UserDao
import com.nsystem.features.usersearch.data.dao.UserRemoteKeysDao
import com.nsystem.features.usersearch.data.model.User
import com.nsystem.features.usersearch.data.model.UserRemoteKeys

@Database(
    entities = [
        User::class,
        UserRemoteKeys::class
               ],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun repoDao(): RepoDao
    abstract fun userDao(): UserDao
    abstract fun userRemoteKeysDaoDao(): UserRemoteKeysDao
}