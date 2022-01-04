package com.nsystem.features.usersearch.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nsystem.features.usersearch.domain.model.Repo

@Entity(tableName = "repo")
data class RepoEntity(

    @PrimaryKey
    override val id: Long,

    @ColumnInfo(name = "full_name")
    override val fullName: String,

    @ColumnInfo(name = "owner_name")
    override val ownerName: String,

    @ColumnInfo(name = "star_count")
    override val starCount: Int,

    @ColumnInfo(name = "fork_count")
    override val forkCount: Int,

    @ColumnInfo(name = "open_issue_count")
    override val openIssueCount: Int,

    @ColumnInfo(name = "repository_url")
    override val repositoryUrl: String
): Repo
