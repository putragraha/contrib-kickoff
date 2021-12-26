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
import com.nsystem.features.usersearch.presentation.adapter.RepoAdapter
import com.nsystem.features.usersearch.presentation.viewmodel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment: Fragment() {

    private var binding: FragmentUserDetailBinding? = null

    private val args: UserDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<UserDetailViewModel>()

    private val repoAdapter by lazy {
        RepoAdapter()
    }

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
        initView()
        initObserver()
        viewModel.getUser(args.username)
        viewModel.getUserRepo(args.username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initView() {
        binding?.rvRepo?.adapter = repoAdapter
    }

    private fun initObserver() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            showUserDetail(it)
        }
        viewModel.reposLiveData.observe(viewLifecycleOwner) {
            repoAdapter.avatarUrl = viewModel.avatarUrl
            repoAdapter.submitList(it)
        }
    }

    private fun showUserDetail(user: User) {
        binding?.apply {
            val followersLabel = "${
                if (user.followers > 1_000) (user.followers / 1_000) else user.followers
            }K Followers"
            val followingLabel = "${
                if (user.following > 1_000) (user.following / 1_000) else user.following
            }K Following"

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