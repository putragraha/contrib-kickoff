package com.nsystem.features.usersearch.domain

import androidx.paging.ExperimentalPagingApi
import com.nsystem.features.usersearch.data.repository.UserSearchRepository
import javax.inject.Inject

// TODO: 05/01/22 Remove use case after the viewmodel removed
class SearchUserUseCase @Inject constructor(private val repository: UserSearchRepository) {

    @ExperimentalPagingApi
    fun invoke(query: String) = repository.getSearchResultStream(query)
}