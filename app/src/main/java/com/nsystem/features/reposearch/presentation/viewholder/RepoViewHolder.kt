package com.nsystem.features.reposearch.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.nsystem.R
import com.nsystem.databinding.ItemRepoBinding
import com.nsystem.features.reposearch.domain.model.Repo

class RepoViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: Repo) {
        val ownerName = repo.ownerName ?: ""
        if (ownerName.isBlank()) binding.tvOwnerName.isVisible = false

        binding.tvRepoName.text = repo.fullName
        binding.tvOwnerName.text = getString(R.string.label_owner_name, ownerName)
        binding.actvStar.text = getQuantityString(R.plurals.label_star, repo.starCount)
        binding.actvFork.text = getQuantityString(R.plurals.label_fork, repo.forkCount)
        binding.actvIssue.text = getQuantityString(R.plurals.label_issue, repo.openIssueCount)
    }

    private fun getString(resId: Int, value: String) = itemView.context.getString(resId, value)

    private fun getQuantityString(resId: Int, value: Int): CharSequence {
        return itemView.context.resources.getQuantityString(resId, value, value)
    }

    companion object {

        fun create(parent: ViewGroup): RepoViewHolder {
            val binding = ItemRepoBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
            )
            return RepoViewHolder(binding)
        }
    }
}