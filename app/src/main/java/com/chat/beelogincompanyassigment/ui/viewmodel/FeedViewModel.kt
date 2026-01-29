package com.chat.beelogincompanyassigment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chat.beelogincompanyassigment.R
import com.chat.beelogincompanyassigment.data.models.Post

class FeedViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    init {
        loadPosts()
    }

    private fun loadPosts() {
        _posts.value = listOf(
            Post(
                "Komal",
                "2h ago",
                "Mumbai",
                R.drawable.logo,
                "Morning Fuel Update",
                "Today's fuel rate is stable. Good day for long drives!",
                22,
                5
            ),
            Post(
                "Nikhil",
                "4h ago",
                "Pune",
                R.drawable.linkedin_372399,
                "New Petrol Pump Installed",
                "A brand new station is now operating on Satara Road.",
                44,
                12
            )
        )
    }
}