package com.chat.beelogincompanyassigment.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.chat.beelogincompanyassigment.Adapter.ProfilePostAdapter
import com.chat.beelogincompanyassigment.databinding.FragmentProfileBinding
import com.chat.beelogincompanyassigment.ui.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupObservers()
        setupLogout()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.name.observe(viewLifecycleOwner) {
            binding.profileName.text = it
        }

        viewModel.bio.observe(viewLifecycleOwner) {
            binding.profileBio.text = it
        }

        viewModel.stats.observe(viewLifecycleOwner) {
            binding.statPosts.text = it.posts
            binding.statFollowers.text = it.followers
            binding.statFollowing.text = it.following
        }

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            binding.profileRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.profileRecycler.adapter = ProfilePostAdapter(posts)
        }
    }

    private fun setupLogout() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout(requireContext())
            requireActivity().finish()
        }
    }
}