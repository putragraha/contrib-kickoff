package com.nsystem.features.reposearch.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nsystem.R
import com.nsystem.databinding.FragmentSearchRepoBinding
import com.nsystem.features.reposearch.presentation.adapter.RepoAdapter
import com.nsystem.features.reposearch.presentation.model.SearchRepoUiAction
import com.nsystem.features.reposearch.presentation.model.SearchRepoUiModel
import com.nsystem.features.reposearch.presentation.model.SearchRepoUiState
import com.nsystem.features.reposearch.presentation.viewmodel.SearchRepoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchRepoFragment: Fragment() {

    private var binding: FragmentSearchRepoBinding? = null

    private val searchRepoViewModel by hiltNavGraphViewModels<SearchRepoViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchRepoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.bindState(
            onQueryChanged = searchRepoViewModel.accept,
            uiState = searchRepoViewModel.state,
            pagingData = searchRepoViewModel.pagingDataFlow,
            onScrollChanged = searchRepoViewModel.accept
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun FragmentSearchRepoBinding.bindState(
        onQueryChanged: (SearchRepoUiAction) -> Unit,
        uiState: StateFlow<SearchRepoUiState>,
        pagingData: Flow<PagingData<SearchRepoUiModel>>,
        onScrollChanged: (SearchRepoUiAction.Scroll) -> Unit
    ) {
        bindSearch { onQueryChanged(it) }
        bindList(uiState, pagingData, onScrollChanged)
    }

    private fun FragmentSearchRepoBinding.bindSearch(onQueryChanged: (SearchRepoUiAction) -> Unit) {
        acetSearch.addTextChangedListener { keyword ->
            if (keyword?.trim()?.isNotBlank() == true) {
                onQueryChanged(SearchRepoUiAction.Search(keyword.toString()))
            }
        }
    }

    private fun FragmentSearchRepoBinding.bindList(
        uiState: StateFlow<SearchRepoUiState>,
        pagingData: Flow<PagingData<SearchRepoUiModel>>,
        onScrollChanged: (SearchRepoUiAction.Scroll) -> Unit
    ) {
        val repoAdapter = RepoAdapter()
        rvSearchResult.run {
            adapter = repoAdapter
            addItemDecoration(getRepoListDivider())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy != 0) onScrollChanged(SearchRepoUiAction.Scroll(uiState.value.query))
                }
            })
        }
        lifecycleScope.launchWhenResumed {
            pagingData.collectLatest(repoAdapter::submitData)
        }
    }

    private fun getRepoListDivider(): DividerItemDecoration {
        val dividerDrawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.layer_repo_list_divider,
            null
        )

        return DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
            dividerDrawable?.let {
                setDrawable(dividerDrawable)
            }
        }
    }
}