package com.nsystem.features.usersearch.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nsystem.features.usersearch.data.model.RepoOld
import com.nsystem.features.usersearch.presentation.viewholder.RepoViewHolder

// TODO: 03/01/22 Modify for new implementation
class RepoAdapter(
    var avatarUrl: String = ""
): ListAdapter<RepoOld, RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.create(avatarUrl, parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RepoOld>() {
            override fun areItemsTheSame(oldItem: RepoOld, newItem: RepoOld): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoOld, newItem: RepoOld): Boolean {
                return oldItem == newItem
            }
        }
    }
}