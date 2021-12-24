package com.nsystem.features.usersearch.presentation.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsystem.databinding.ItemUserResultBinding
import com.nsystem.features.usersearch.presentation.model.UserView

class UserViewHolder(
    private val binding: ItemUserResultBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(userView: UserView) = with(binding) {
        tvName.text = userView.name
        tvUsername.text = userView.username
        tvBio.text = userView.bio
        tvLocation.text = userView.location
        tvEmail.text = userView.email
        sivAvatar.loadImage(userView.avatarUrl)
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(binding.root)
            .load(url)
            .into(this)
    }
}