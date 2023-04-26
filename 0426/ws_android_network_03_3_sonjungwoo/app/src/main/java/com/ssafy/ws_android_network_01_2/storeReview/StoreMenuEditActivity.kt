package com.ssafy.ws_android_network_01_2.storeReview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ssafy.ws_android_network_01_2.ApplicationClass
import com.ssafy.ws_android_network_01_2.dao.DB
import com.ssafy.ws_android_network_01_2.databinding.ActivityStoreMenuEditBinding
import com.ssafy.ws_android_network_01_2.service.StoreMenuService


private const val TAG = "StoreMenuEditActivity_싸피"

class StoreMenuEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoreMenuEditBinding
    lateinit var storeMenuService: StoreMenuService
    var position = -1
    lateinit var mode: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreMenuEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeMenuService = ApplicationClass.retrofit.create(StoreMenuService::class.java)

        setMode()
        viewUpdate()
        btnInit()
        Log.d(TAG, "check: ${DB.StoreMenus.toString()}")

    }

    private fun viewUpdate() {
        if (mode == "UPDATE") {
//            binding.priceInp.setText(DB.StoreMenus[position].price)
//            binding.reviewInp.setText(DB.StoreMenus[position].name)
        }
    }

    private fun btnInit() {
        if (mode == "CREATE") {
//            binding.saveBtn.setOnClickListener {
//                var content = binding.reviewInp.text.toString()
//                var score = binding.ratingInp.rating.toInt()
//                var storeReview =
//                    StoreReviewDTO(content, DB.StoreReviews.size + 1, score, DB.Store.id)
//                CoroutineScope(Dispatchers.IO).launch {
//                    storeReviewInterface.postReview(storeReview)
//                }
//                finish()
//            }
            binding.deleteBtn.visibility = View.GONE
            binding.cancelBtn.setOnClickListener { finish() }
        } else {
            binding.saveBtn.setOnClickListener {
//                var content = binding.reviewInp.text.toString()
//                var score = binding.ratingInp.rating.toInt()
//                var storeReview =
//                    StoreReviewDTO(content, DB.StoreReviews[position].id, score, DB.Store.id)
//                CoroutineScope(Dispatchers.IO).launch {
//                    storeReviewInterface.updateReview(
//                        DB.StoreReviews[position].id.toString(),
//                        storeReview
//                    )
//                }
                finish()
            }
            binding.deleteBtn.setOnClickListener {
//                CoroutineScope(Dispatchers.IO).launch {
//                    storeReviewInterface.deleteReview(DB.StoreReviews[position].id.toString())
//                }
                finish()
            }
            binding.cancelBtn.setOnClickListener { finish() }

        }
    }

    private fun setMode() {
        position = intent.getIntExtra("position", -1)

        if (position == -1) {
            mode = "CREATE"
        } else {
            mode = "UPDATE"
        }
        Log.d(TAG, "position: $position")
        Log.d(TAG, "mode: $mode")

    }
}
