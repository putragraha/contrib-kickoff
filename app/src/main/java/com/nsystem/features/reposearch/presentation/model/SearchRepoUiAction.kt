package com.nsystem.features.reposearch.presentation.model

sealed class SearchRepoUiAction {
    data class Search(val query: String): SearchRepoUiAction()
    data class Scroll(val currentQuery: String): SearchRepoUiAction()
}
