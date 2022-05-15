package com.nsystem.features.reposearch.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nsystem.features.reposearch.presentation.model.SearchRepoUiModel
import com.nsystem.features.reposearch.presentation.viewholder.RepoViewHolder

class RepoAdapter: PagingDataAdapter<SearchRepoUiModel, RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        when (val uiModel = getItem(position)) {
            is SearchRepoUiModel.RepoItem -> holder.bind(uiModel.repo)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<SearchRepoUiModel>() {

            override fun areItemsTheSame(
                oldItem: SearchRepoUiModel,
                newItem: SearchRepoUiModel
            ): Boolean {
                return when {
                    oldItem is SearchRepoUiModel.RepoItem
                            && newItem is SearchRepoUiModel.RepoItem -> {
                        oldItem.repo.id == newItem.repo.id
                    }
                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: SearchRepoUiModel,
                newItem: SearchRepoUiModel
            ): Boolean {
                return when {
                    oldItem is SearchRepoUiModel.RepoItem
                            && newItem is SearchRepoUiModel.RepoItem -> {
                        oldItem.repo == newItem.repo
                    }
                    else -> false
                }
            }
        }
    }
}