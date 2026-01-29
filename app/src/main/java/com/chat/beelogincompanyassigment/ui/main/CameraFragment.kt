package com.chat.beelogincompanyassigment.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.chat.beelogincompanyassigment.R
import com.chat.beelogincompanyassigment.databinding.FragmentCameraBinding
import com.chat.beelogincompanyassigment.ui.viewmodel.CameraViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var viewModel: CameraViewModel

    private val CAMERA_REQ = 101
    private val GALLERY_REQ = 102

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCameraBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[CameraViewModel::class.java]

        setupClicks()
        observeViewModel()

        return binding.root
    }

    private fun setupClicks() {
        binding.btnOpenCamera.setOnClickListener {
            viewModel.openCamera()
        }

        binding.btnOpenGallery.setOnClickListener {
            viewModel.openGallery()
        }
    }

    private fun observeViewModel() {
        viewModel.openIntent.observe(viewLifecycleOwner) { (intent, requestCode) ->
            startActivityForResult(intent, requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) return

        when (requestCode) {

            CAMERA_REQ -> {
                val bmp = data?.extras?.get("data") as Bitmap
                goToPreview(bmp)
            }

            GALLERY_REQ -> {
                val uri = data?.data
                goToPreview(uri)
            }
        }
    }

    private fun goToPreview(image: Any?) {
        val intent = Intent(requireContext(), CameraPreviewActivity::class.java)

        when (image) {
            is Bitmap -> intent.putExtra("bitmap", image)
            else       -> intent.putExtra("uri", image.toString())
        }

        startActivity(intent)
    }
}