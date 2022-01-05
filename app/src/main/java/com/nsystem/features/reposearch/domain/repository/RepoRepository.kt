package com.nsystem.features.reposearch.domain.repository

import androidx.paging.PagingData
import com.nsystem.features.reposearch.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository<T : Repo> {

    suspend fun searchRepo(query: String): Flow<PagingData<T>>
}