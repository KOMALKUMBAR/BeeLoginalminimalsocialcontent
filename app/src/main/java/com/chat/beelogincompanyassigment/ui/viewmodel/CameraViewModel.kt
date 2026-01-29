package com.chat.beelogincompanyassigment.ui.viewmodel

import android.content.Intent
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    private val _openIntent = MutableLiveData<Pair<Intent, Int>>()
    val openIntent: LiveData<Pair<Intent, Int>> = _openIntent

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        _openIntent.value = intent to 101
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
        _openIntent.value = intent to 102
    }
}