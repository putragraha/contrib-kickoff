package com.nsystem.features.reposearch.domain.repository

import androidx.paging.PagingData
import com.nsystem.features.reposearch.data.model.RepoEntity
import com.nsystem.features.reposearch.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    suspend fun searchRepo(query: String): Flow<PagingData<RepoEntity>>
}