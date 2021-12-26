package com.nsystem.features.usersearch.presentation.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsystem.databinding.ItemUserResultBinding
import com.nsystem.features.usersearch.data.model.User

class UserViewHolder(
    private val binding: ItemUserResultBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) = with(binding) {
        val username = "@${user.login}"

        tvName.text = user.name
        tvUsername.text = username
        tvBio.text = user.bio
        tvLocation.text = user.location
        tvEmail.text = user.email
        sivAvatar.loadImage(user.avatarUrl)
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(binding.root)
            .load(url)
            .into(this)
    }
}