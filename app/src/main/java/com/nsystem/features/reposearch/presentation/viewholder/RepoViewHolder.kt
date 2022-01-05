package com.nsystem.features.reposearch.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsystem.R
import com.nsystem.databinding.ItemRepoBinding
import com.nsystem.features.reposearch.domain.model.Repo

class RepoViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: Repo) {
        binding.tvRepoName.text = repo.fullName
        repo.ownerName?.let { ownerName ->
            binding.tvOwnerName.text = getText(R.string.label_owner_name, ownerName)
        }
        binding.actvStar.text = getText(R.plurals.label_star, repo.starCount)
        binding.actvFork.text = getText(R.plurals.label_fork, repo.forkCount)
        binding.actvIssue.text = getText(R.plurals.label_issue, repo.openIssueCount)
    }

    private fun getText(resId: Int, value: Any) = itemView.context.getString(resId, value)

    companion object {

        fun create(parent: ViewGroup): RepoViewHolder {
            val binding = ItemRepoBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
            )
            return RepoViewHolder(binding)
        }
    }
}