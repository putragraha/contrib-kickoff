package com.nsystem.features.usersearch.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsystem.features.usersearch.data.model.User
import com.nsystem.features.usersearch.domain.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {

    val usersLiveData = MutableLiveData<User>()

    fun getUser(username: String) {
        viewModelScope.launch {
            getUserUseCase.invoke(username).collect {
                usersLiveData.postValue(it)
            }
        }
    }
}