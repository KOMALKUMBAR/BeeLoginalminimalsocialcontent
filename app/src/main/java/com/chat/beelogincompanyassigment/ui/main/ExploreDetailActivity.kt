package com.chat.beelogincompanyassigment.ui.main

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chat.beelogincompanyassigment.R

class ExploreDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_detail)

        val imgView = findViewById<ImageView>(R.id.detailImage)
        imgView.setImageResource(intent.getIntExtra("img", 0))
    }
}