package com.chat.beelogincompanyassigment.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chat.beelogincompanyassigment.R
import com.chat.beelogincompanyassigment.ui.main.MapMarker

class MapViewModel : ViewModel() {

    private val _markers = MutableLiveData<List<MapMarker>>()
    val markers: LiveData<List<MapMarker>> get() = _markers

    private val _selectedMarker = MutableLiveData<MapMarker?>()
    val selectedMarker: LiveData<MapMarker?> get() = _selectedMarker

    init {
        loadMarkers()
    }

    private fun loadMarkers() {
        _markers.value = listOf(
            MapMarker("Fuel Station", R.drawable.sample_post1, 300f, 500f),
            MapMarker("Petrol Offer", R.drawable.sample_post2, 550f, 700f),
            MapMarker("Auto Service", R.drawable.sample_post3, 200f, 300f)
        )
    }

    fun selectMarker(marker: MapMarker) {
        _selectedMarker.value = marker
    }
}