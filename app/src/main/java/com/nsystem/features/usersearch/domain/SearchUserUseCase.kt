package com.nsystem.features.usersearch.domain

import androidx.paging.ExperimentalPagingApi
import com.nsystem.features.usersearch.data.repository.UserSearchRepository
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(private val repository: UserSearchRepository) {

    @ExperimentalPagingApi
    fun invoke(query: String) = repository.getSearchResultStream(query)
}