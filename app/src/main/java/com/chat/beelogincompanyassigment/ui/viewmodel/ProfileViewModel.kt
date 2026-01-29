package com.chat.beelogincompanyassigment.ui.viewmodel


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chat.beelogincompanyassigment.R

class ProfileViewModel : ViewModel() {

    private val _name = MutableLiveData("Komal Kumbar")
    val name: LiveData<String> get() = _name

    private val _bio = MutableLiveData("Fuel updates • Petrol pump posts • Auto service content")
    val bio: LiveData<String> get() = _bio

    private val _posts = MutableLiveData<List<Int>>(
        listOf(
            R.drawable.sample_post1,
            R.drawable.sample_post2,
            R.drawable.sample_post3,
            R.drawable.sample_post1,
            R.drawable.sample_post2,
            R.drawable.sample_post3,
        )
    )
    val posts: LiveData<List<Int>> get() = _posts

    private val _stats = MutableLiveData(ProfileStats("24", "1.2k", "180"))
    val stats: LiveData<ProfileStats> get() = _stats

    fun logout(context: Context) {
        val sp = context.getSharedPreferences("login", Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }
}

data class ProfileStats(
    val posts: String,
    val followers: String,
    val following: String
)