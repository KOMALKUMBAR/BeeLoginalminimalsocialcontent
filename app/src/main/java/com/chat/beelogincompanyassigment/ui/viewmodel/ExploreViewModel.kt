package com.chat.beelogincompanyassigment.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chat.beelogincompanyassigment.R

class ExploreViewModel : ViewModel() {

    private val categories = listOf("Trending", "Nature", "Architecture", "Food", "Travel")

    private val dataMap = mapOf(
        "Trending" to listOf(
            R.drawable.pinterest_372404, R.drawable.twitter_527595__1_, R.drawable.pinterest_372404
        ),
        "Nature" to listOf(
            R.drawable.pinterest_372404, R.drawable.pinterest_372404, R.drawable.twitter_527595__1_
        ),
        "Architecture" to listOf(
            R.drawable.twitter_527595__1_, R.drawable.pinterest_372404, R.drawable.twitter_527595__1_
        ),
        "Food" to listOf(
            R.drawable.logo, R.drawable.pinterest_372404, R.drawable.logo
        ),
        "Travel" to listOf(
            R.drawable.linkedin_372399, R.drawable.logo, R.drawable.linkedin_372399
        )
    )

    /** LiveData for categories */
    private val _categoriesLive = MutableLiveData<List<String>>(categories)
    val categoriesLive: LiveData<List<String>> get() = _categoriesLive

    /** LiveData for current image list */
    private val _imageList = MutableLiveData<List<Int>>(dataMap["Trending"])
    val imageList: LiveData<List<Int>> get() = _imageList

    fun onCategorySelected(category: String) {
        _imageList.value = dataMap[category]
    }
}