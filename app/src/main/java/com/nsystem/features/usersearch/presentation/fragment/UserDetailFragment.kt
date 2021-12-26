package com.nsystem.features.usersearch.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nsystem.databinding.FragmentUserDetailBinding
import com.nsystem.features.usersearch.data.model.User
import com.nsystem.features.usersearch.presentation.viewmodel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment: Fragment() {

    private var binding: FragmentUserDetailBinding? = null

    private val args: UserDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<UserDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser(args.username)
        initObserver()
        // TODO: 26/12/21 Get repo list from API
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initObserver() {
        viewModel.usersLiveData.observe(viewLifecycleOwner) {
            showUserDetail(it)
        }
    }

    private fun showUserDetail(user: User) {
        binding?.apply {
            val followersLabel = "${(user.followers / 1_000)}K Followers"
            val followingLabel = "${(user.following / 1_000)}K Following"

            sivAvatar.loadImage(user.avatarUrl)
            tvName.text = user.name
            tvUsername.text = user.login
            tvBio.text = user.bio
            actvFollowers.text = followersLabel
            actvFollowing.text = followingLabel
            actvLocation.text = user.location
            actvEmail.text = user.email
        }
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this@UserDetailFragment)
            .load(url)
            .into(this)
    }
}