package com.nsystem.features.usersearch.domain

import com.nsystem.features.usersearch.data.repository.UserSearchRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserSearchRepository) {

    suspend fun invoke(username: String) = repository.getUser(username)
}