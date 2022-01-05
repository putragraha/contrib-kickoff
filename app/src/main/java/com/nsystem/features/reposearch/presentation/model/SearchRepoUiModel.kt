package com.nsystem.features.reposearch.presentation.model

import com.nsystem.features.reposearch.domain.model.Repo

sealed class SearchRepoUiModel {
    data class RepoItem(val repo: Repo): SearchRepoUiModel()
}
