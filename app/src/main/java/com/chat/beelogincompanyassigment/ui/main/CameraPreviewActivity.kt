package com.chat.beelogincompanyassigment.ui.main

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chat.beelogincompanyassigment.databinding.ActivityCameraPreviewBinding
import com.chat.beelogincompanyassigment.ui.viewmodel.CameraPreviewViewModel

class CameraPreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraPreviewBinding
    private val viewModel: CameraPreviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readIntentData()
        observeViewModel()

        binding.btnPost.setOnClickListener {
            finish() // mock upload
        }
    }

    private fun readIntentData() {
        val bitmap = intent.getParcelableExtra<Bitmap>("bitmap")
        val uriString = intent.getStringExtra("uri")

        if (bitmap != null) {
            viewModel.setBitmap(bitmap)
        } else if (uriString != null) {
            viewModel.setUri(Uri.parse(uriString))
        }
    }

    private fun observeViewModel() {
        viewModel.imageBitmap.observe(this) { bmp ->
            if (bmp != null) binding.previewImage.setImageBitmap(bmp)
        }

        viewModel.imageUri.observe(this) { uri ->
            if (uri != null) binding.previewImage.setImageURI(uri)
        }
    }
}