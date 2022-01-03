package com.nsystem.features.usersearch.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsystem.R
import com.nsystem.databinding.ItemRepoOldBinding
import com.nsystem.features.usersearch.data.model.Repo

class RepoViewHolder(
    avatarUrl: String,
    private val binding: ItemRepoOldBinding
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.sivAvatar.loadImage(avatarUrl)
    }

    fun bind(repo: Repo) {
        binding.tvProjectName.text = repo.projectName
        binding.tvDescription.text = repo.description
        binding.actvStar.text = repo.stars.toString()
        binding.tvLastUpdated.text = repo.updatedAt
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(itemView)
            .load(url)
            .into(this)
    }

    companion object {

        fun create(avatarUrl: String, parent: ViewGroup): RepoViewHolder {
            val binding = ItemRepoOldBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_repo_old, parent, false)
            )
            return RepoViewHolder(avatarUrl, binding)
        }
    }
}