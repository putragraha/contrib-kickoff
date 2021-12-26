package com.nsystem.features.usersearch.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nsystem.R
import com.nsystem.databinding.FragmentUserListBinding
import com.nsystem.features.usersearch.presentation.adapter.UserAdapter
import com.nsystem.features.usersearch.presentation.viewmodel.UserSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment: Fragment() {

    private var binding: FragmentUserListBinding? = null

    private val viewModel by viewModels<UserSearchViewModel>()

    private val userAdapter by lazy {
        UserAdapter {
            val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvSearchResult?.run {
            adapter = userAdapter
            addItemDecoration(getUserListDivider())
        }
        binding?.acetSearch?.addTextChangedListener {
            lifecycleScope.launch {
                viewModel.searchUser(it.toString())
            }
        }
        viewModel.usersLiveData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                userAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getUserListDivider() = DividerItemDecoration(
        context,
        RecyclerView.VERTICAL
    ).apply {
        setDrawable(resources.getDrawable(R.drawable.layer_user_list_divider, null))
    }
}