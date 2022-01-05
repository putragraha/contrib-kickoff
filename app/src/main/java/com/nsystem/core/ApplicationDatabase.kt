package com.nsystem.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nsystem.features.usersearch.data.dao.RepoDao
import com.nsystem.features.usersearch.data.dao.RepoRemoteKeysDao
import com.nsystem.features.usersearch.data.model.RepoEntity
import com.nsystem.features.usersearch.data.model.RepoRemoteKeysEntity

@Database(
    entities = [RepoEntity::class, RepoRemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun repoDao(): RepoDao
    abstract fun repoRemoteKeysDao(): RepoRemoteKeysDao
}