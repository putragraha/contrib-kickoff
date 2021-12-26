package com.nsystem.features.usersearch.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsystem.R
import com.nsystem.databinding.ItemUserResultBinding
import com.nsystem.features.usersearch.data.model.User

class UserViewHolder(
    private val binding: ItemUserResultBinding,
    private val itemClick: (String) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) = with(binding) {
        val username = "@${user.login}"

        itemView.setOnClickListener { itemClick(user.login) }
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

    companion object {

        fun create(parent: ViewGroup, itemClick: (String) -> Unit): UserViewHolder {
            val binding = ItemUserResultBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_user_result, parent, false)
            )
            return UserViewHolder(binding, itemClick)
        }
    }
}