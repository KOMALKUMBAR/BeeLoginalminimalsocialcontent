package com.chat.beelogincompanyassigment.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {

    private val _selectedNavigationIndex = MutableLiveData<Int>(0)
    val selectedNavigationIndex: LiveData<Int> = _selectedNavigationIndex

    fun selectNavigationItem(index: Int) {
        if (_selectedNavigationIndex.value != index) {
            _selectedNavigationIndex.value = index
        }
    }

    fun getCurrentIndex(): Int = _selectedNavigationIndex.value ?: 0
}