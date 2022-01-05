package com.nsystem.features.usersearch.data.mapper

import com.nsystem.features.usersearch.data.model.RepoEntity
import com.nsystem.features.usersearch.data.model.RepoRemoteKeysEntity
import com.nsystem.features.usersearch.data.model.RepoSearchItemsResponse

fun List<RepoSearchItemsResponse>.toRepoRemoteKeysEntities(
    prevKey: Int?,
    nextKey: Int?
) = this.map { RepoRemoteKeysEntity(it.id, prevKey, nextKey) }

fun List<RepoSearchItemsResponse>.toRepoEntities() = this.map { repoSearchItemResponse ->
    RepoEntity(
        id = repoSearchItemResponse.id,
        fullName = repoSearchItemResponse.fullName,
        description = repoSearchItemResponse.description,
        ownerName = repoSearchItemResponse.owner?.login,
        starCount = repoSearchItemResponse.stargazersCount,
        forkCount = repoSearchItemResponse.forksCount,
        openIssueCount = repoSearchItemResponse.openIssuesCount,
        repositoryUrl = repoSearchItemResponse.url
    )
}