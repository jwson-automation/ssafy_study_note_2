package com.ssafy.ws_android_network_01_2.storeReview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.ws_android_network_01_2.R
import com.ssafy.ws_android_network_01_2.databinding.ActivityStoreReviewEditBinding

class StoreReviewEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoreReviewEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreReviewEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {  }
        binding.deleteBtn.setOnClickListener {  }
        binding.cancelBtn.setOnClickListener { finish() }

    }
}