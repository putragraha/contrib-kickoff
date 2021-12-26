package com.nsystem.features.usersearch.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nsystem.features.usersearch.data.model.User
import com.nsystem.features.usersearch.domain.SearchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
): ViewModel() {

    val usersLiveData = MutableLiveData<PagingData<User>>()

    @ExperimentalPagingApi
    suspend fun searchUser(query: String) {
        searchUserUseCase.invoke(query)
            .cachedIn(viewModelScope)
            .collect { usersLiveData.postValue(it) }
    }
}