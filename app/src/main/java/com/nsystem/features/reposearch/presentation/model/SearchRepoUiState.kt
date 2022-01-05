package com.nsystem.features.reposearch.presentation.model

import com.nsystem.features.reposearch.presentation.viewmodel.SearchRepoViewModel

data class SearchRepoUiState(
    val query: String = SearchRepoViewModel.DEFAULT_QUERY,
    val lastQueryScrolled: String = SearchRepoViewModel.DEFAULT_QUERY,
    val hasNotScrolledForCurrentSearch: Boolean = false
)
