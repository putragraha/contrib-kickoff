package com.nsystem.features.usersearch.domain.repository

import androidx.paging.PagingData
import com.nsystem.features.usersearch.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    suspend fun searchRepo(query: String): Flow<PagingData<Repo>>
}