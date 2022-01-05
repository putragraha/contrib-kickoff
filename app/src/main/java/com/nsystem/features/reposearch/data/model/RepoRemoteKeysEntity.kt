package com.nsystem.features.reposearch.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_remote_keys")
data class RepoRemoteKeysEntity(

    @PrimaryKey
    @ColumnInfo(name = "repo_id")
    val repoId: Long,

    @ColumnInfo(name = "prev_key")
    val prevKey: Int?,

    @ColumnInfo(name = "next_key")
    val nextKey: Int?
)
