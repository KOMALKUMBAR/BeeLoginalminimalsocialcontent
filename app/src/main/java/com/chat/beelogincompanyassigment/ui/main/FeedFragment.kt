package com.chat.beelogincompanyassigment.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chat.beelogincompanyassigment.Adapter.PostAdapter
import com.chat.beelogincompanyassigment.R
import com.chat.beelogincompanyassigment.databinding.FragmentFeedBinding
import com.chat.beelogincompanyassigment.ui.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()
    private lateinit var binding: FragmentFeedBinding
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFeedBinding.inflate(inflater, container, false)

        binding.feedRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = PostAdapter(emptyList())
        binding.feedRecycler.adapter = adapter

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.posts.observe(viewLifecycleOwner) { postList ->
            adapter.updateData(postList)
        }
    }
}