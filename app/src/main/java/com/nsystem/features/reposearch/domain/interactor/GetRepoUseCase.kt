package com.nsystem.features.reposearch.domain.interactor

import com.nsystem.features.reposearch.domain.repository.RepoRepository
import javax.inject.Inject

class GetRepoUseCase @Inject constructor(private val repoRepository: RepoRepository) {

    suspend fun invoke(query: String) = repoRepository.searchRepo(query)
}