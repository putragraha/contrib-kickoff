package com.nsystem.features.usersearch.domain

import com.nsystem.features.usersearch.data.repository.RepoRepository
import javax.inject.Inject

// TODO: 03/01/22 Remove on User Detail removed
class GetUserRepoUseCase @Inject constructor(private val repository: RepoRepository) {

    suspend fun invoke(username: String) = repository.getUserRepo(username)
}