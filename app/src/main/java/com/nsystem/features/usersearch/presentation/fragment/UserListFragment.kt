package com.nsystem.features.usersearch.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nsystem.R
import com.nsystem.databinding.FragmentUserListBinding
import com.nsystem.features.usersearch.presentation.adapter.UserAdapter

class UserListFragment: Fragment() {

    private var binding: FragmentUserListBinding? = null

    private val userAdapter by lazy {
        UserAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvSearchResult?.run {
            adapter = userAdapter
            addItemDecoration(getUserListDivider())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun getUserListDivider() = DividerItemDecoration(
        context,
        RecyclerView.VERTICAL
    ).apply {
        setDrawable(resources.getDrawable(R.drawable.layer_user_list_divider, null))
    }
}