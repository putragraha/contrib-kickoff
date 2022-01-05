package com.nsystem.features.usersearch.domain.interactor

import com.nsystem.features.usersearch.domain.model.Repo
import com.nsystem.features.usersearch.domain.repository.RepoRepository
import javax.inject.Inject

class GetRepoUseCase @Inject constructor(private val repoRepository: RepoRepository<Repo>) {

    suspend fun invoke(query: String) = repoRepository.searchRepo(query)
}