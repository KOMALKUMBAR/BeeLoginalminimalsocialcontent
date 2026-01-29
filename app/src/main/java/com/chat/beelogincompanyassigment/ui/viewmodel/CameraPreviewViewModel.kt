package com.chat.beelogincompanyassigment.ui.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraPreviewViewModel : ViewModel() {

    private val _imageBitmap = MutableLiveData<Bitmap?>()
    val imageBitmap: LiveData<Bitmap?> get() = _imageBitmap

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> get() = _imageUri

    fun setBitmap(bmp: Bitmap?) {
        _imageBitmap.value = bmp
    }

    fun setUri(uri: Uri?) {
        _imageUri.value = uri
    }
}