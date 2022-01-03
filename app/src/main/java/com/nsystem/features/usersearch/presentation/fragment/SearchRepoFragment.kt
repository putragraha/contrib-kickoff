package com.nsystem.features.usersearch.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}