package com.nsystem.features.reposearch.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.nsystem.features.reposearch.domain.interactor.GetRepoUseCase
import com.nsystem.features.reposearch.presentation.model.SearchRepoUiAction
import com.nsystem.features.reposearch.presentation.model.SearchRepoUiModel
import com.nsystem.features.reposearch.presentation.model.SearchRepoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchRepoViewModel @Inject constructor(
    private val getRepoUseCase: GetRepoUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val pagingDataFlow: Flow<PagingData<SearchRepoUiModel>>

    val state: StateFlow<SearchRepoUiState>

    val accept: (SearchRepoUiAction) -> Unit

    init {
        val initialQuery = savedStateHandle.get(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        val lastQueryScrolled = savedStateHandle.get(LAST_QUERY_SCROLLED) ?: DEFAULT_QUERY
        val actionSharedFlow = MutableSharedFlow<SearchRepoUiAction>()
        val searchFlow = actionSharedFlow
            .filterIsInstance<SearchRepoUiAction.Search>()
            .distinctUntilChanged()
            .debounce(1_500)
            .onStart { emit(SearchRepoUiAction.Search(initialQuery)) }
        val queriesScrolledFlow = actionSharedFlow
            .filterIsInstance<SearchRepoUiAction.Scroll>()
            .distinctUntilChanged()
            .shareIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
                replay = 1
            )
            .onStart { emit(SearchRepoUiAction.Scroll(lastQueryScrolled)) }

        pagingDataFlow = searchFlow
            .flatMapLatest { searchRepo(it.query) }
            .cachedIn(viewModelScope)

        state = combine(searchFlow, queriesScrolledFlow, ::Pair)
            .map { (search, scroll) ->
                SearchRepoUiState(
                    query = search.query,
                    lastQueryScrolled = scroll.currentQuery,
                    hasNotScrolledForCurrentSearch = search.query != scroll.currentQuery
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
                initialValue = SearchRepoUiState()
            )

        accept = { action ->
            viewModelScope.launch { actionSharedFlow.emit(action) }
        }
    }

    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
        savedStateHandle[LAST_QUERY_SCROLLED] = state.value.lastQueryScrolled
        super.onCleared()
    }

    private suspend fun searchRepo(query: String): Flow<PagingData<SearchRepoUiModel>> {
        return getRepoUseCase.invoke(query)
            .map { pagingData -> pagingData.map { SearchRepoUiModel.RepoItem(it) } }
    }

    companion object {
        const val DEFAULT_QUERY = ""
        private const val LAST_SEARCH_QUERY = "last_search_query"
        private const val LAST_QUERY_SCROLLED = "last_query_scrolled"
    }
}