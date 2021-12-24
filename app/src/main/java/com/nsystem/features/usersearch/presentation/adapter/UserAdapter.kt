package com.nsystem.features.usersearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nsystem.R
import com.nsystem.databinding.ItemUserResultBinding
import com.nsystem.features.usersearch.presentation.model.UserView
import com.nsystem.features.usersearch.presentation.viewholder.UserViewHolder

class UserAdapter: ListAdapter<UserView, UserViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserResultBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_result, parent, false)
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<UserView>() {
            override fun areItemsTheSame(oldItem: UserView, newItem: UserView): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areContentsTheSame(oldItem: UserView, newItem: UserView): Boolean {
                return oldItem == newItem
            }
        }
    }
}