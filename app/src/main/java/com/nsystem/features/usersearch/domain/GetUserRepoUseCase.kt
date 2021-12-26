package com.nsystem.features.usersearch.domain

import com.nsystem.features.usersearch.data.repository.RepoRepository
import javax.inject.Inject

class GetUserRepoUseCase @Inject constructor(private val repository: RepoRepository) {

    suspend fun invoke(username: String) = repository.getUserRepo(username)
}