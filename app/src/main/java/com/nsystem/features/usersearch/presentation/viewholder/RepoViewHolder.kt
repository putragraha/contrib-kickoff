package com.nsystem.features.usersearch.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsystem.R
import com.nsystem.databinding.ItemRepoOldBinding
import com.nsystem.features.usersearch.data.model.RepoOld

class RepoViewHolder(
    avatarUrl: String,
    private val binding: ItemRepoOldBinding
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.sivAvatar.loadImage(avatarUrl)
    }

    fun bind(repoOld: RepoOld) {
        binding.tvProjectName.text = repoOld.projectName
        binding.tvDescription.text = repoOld.description
        binding.actvStar.text = repoOld.stars.toString()
        binding.tvLastUpdated.text = repoOld.updatedAt
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