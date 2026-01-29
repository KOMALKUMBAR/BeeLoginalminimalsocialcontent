package com.chat.beelogincompanyassigment.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chat.beelogincompanyassigment.R
import com.chat.beelogincompanyassigment.databinding.ActivityHomeBinding
import com.chat.beelogincompanyassigment.ui.viewmodel.NavigationViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        observeViewModel()

        // Load default tab only once
        if (savedInstanceState == null) {
            viewModel.selectNavigationItem(0)   // Default Feed tab
        }
    }

    private fun setupNavigation() {
        binding.segmentedNav.onItemSelected = { index ->
            viewModel.selectNavigationItem(index)
        }
    }

    private fun observeViewModel() {
        viewModel.selectedNavigationIndex.observe(this) { index ->
            navigateToFragment(index)
        }
    }

    private fun navigateToFragment(index: Int) {
        val fragment = when (index) {
            0 -> FeedFragment()
            1 -> ExploreFragment()
            2 -> MapFragment()
            3 -> ProfileFragment()
            4 -> CameraFragment()
            else -> FeedFragment()
        }
        loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}