package com.nsystem.features.usersearch.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nsystem.features.usersearch.domain.model.Repo
import com.nsystem.features.usersearch.presentation.viewholder.RepoViewHolder

class RepoAdapter(
    private val itemClick: (String) -> Unit
): PagingDataAdapter<Repo, RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.let {
            holder.bind(repo)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.fullName == newItem.fullName
            }
        }
    }
}