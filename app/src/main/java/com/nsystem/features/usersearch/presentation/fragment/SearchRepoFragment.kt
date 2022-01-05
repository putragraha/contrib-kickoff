package com.nsystem.features.usersearch.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nsystem.R
import com.nsystem.databinding.FragmentSearchRepoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchRepoFragment: Fragment() {

    private var binding: FragmentSearchRepoBinding? = null

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
        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupView() {
        binding?.rvSearchResult?.run {
            addItemDecoration(getRepoListDivider())
        }
    }

    private fun getRepoListDivider(): DividerItemDecoration {
        val dividerDrawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.layer_user_list_divider,
            null
        )

        return DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
            dividerDrawable?.let {
                setDrawable(dividerDrawable)
            }
        }
    }
}