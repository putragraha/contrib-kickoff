package com.nsystem.features.reposearch.domain.model

interface Repo {
    val id: Long
    val fullName: String?
    val description: String?
    val ownerName: String?
    val starCount: Int
    val forkCount: Int
    val openIssueCount: Int
    val repositoryUrl: String?
    override fun equals(other: Any?): Boolean
}
