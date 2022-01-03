package com.nsystem.features.usersearch.domain.model

interface Repo {
    val id: Long
    val fullName: String
    val ownerName: String
    val starCount: Int
    val forkCount: Int
    val openIssueCount: Int
    val repositoryUrl: String
}
