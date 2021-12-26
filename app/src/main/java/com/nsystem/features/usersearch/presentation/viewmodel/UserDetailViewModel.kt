package com.nsystem.features.usersearch.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsystem.features.usersearch.data.model.Repo
import com.nsystem.features.usersearch.data.model.User
import com.nsystem.features.usersearch.domain.GetUserRepoUseCase
import com.nsystem.features.usersearch.domain.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserRepoUseCase: GetUserRepoUseCase
): ViewModel() {

    val userLiveData = MutableLiveData<User>()

    val reposLiveData = MutableLiveData<List<Repo>>()

    var avatarUrl = ""

    fun getUser(username: String) {
        viewModelScope.launch {
            getUserUseCase.invoke(username).collect {
                userLiveData.postValue(it)
                avatarUrl = it.avatarUrl
            }
        }
    }

    fun getUserRepo(username: String) {
        viewModelScope.launch {
            getUserRepoUseCase.invoke(username).collect {
                reposLiveData.postValue(it)
            }
        }
    }
}