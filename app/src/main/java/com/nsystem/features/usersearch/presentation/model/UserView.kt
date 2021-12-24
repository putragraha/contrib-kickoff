package com.nsystem.features.usersearch.presentation.model

data class UserView(
    val username: String,
    val name: String,
    val bio: String,
    val location: String,
    val email: String,
    val avatarUrl: String
)
