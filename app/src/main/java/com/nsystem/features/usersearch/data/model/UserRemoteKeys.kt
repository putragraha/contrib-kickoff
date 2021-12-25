package com.nsystem.features.usersearch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_remote_keys")
data class UserRemoteKeys(
    @PrimaryKey val login: String,
    val prevKey: Int?,
    val nextKey: Int?
)