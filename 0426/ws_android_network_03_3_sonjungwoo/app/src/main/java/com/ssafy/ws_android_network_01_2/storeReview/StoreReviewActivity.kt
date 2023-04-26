package com.ssafy.ws_android_network_01_2.storeReview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ssafy.ws_android_network_01_2.ApplicationClass
import com.ssafy.ws_android_network_01_2.dao.DB
import com.ssafy.ws_android_network_01_2.databinding.ActivityStoreReviewBinding
import com.ssafy.ws_android_network_01_2.databinding.ReviewListBinding
import com.ssafy.ws_android_network_01_2.dto.StoreReviewDTO
import com.ssafy.ws_android_network_01_2.service.StoreService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "StoreReviewActivity"

class StoreReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoreReviewBinding
    lateinit var storeInterface:StoreService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeInterface = ApplicationClass.retrofit.create(StoreService::class.java)

        loadStoreReviews()

        binding.button.setOnClickListener {
            startActivity(Intent(this, StoreReviewEditActivity::class.java))
        }


    }

    override fun onResume() {
        super.onResume()
        loadStoreReviews()
    }

    private fun loadStoreReviews(){
        CoroutineScope(Dispatchers.Main).launch {
            val response = storeInterface.selectStoreReviews("1")
            if (response.isSuccessful) {
                DB.StoreReviews = response.body()!!
                binding.rcv.adapter = rcvAdapter(this@StoreReviewActivity, DB.StoreReviews)
                binding.rcv.layoutManager = LinearLayoutManager(this@StoreReviewActivity,
                    LinearLayoutManager.VERTICAL,false)
            }
        }
    }
}



class rcvAdapter(val context: Context, val reviews: MutableList<StoreReviewDTO>) :
    RecyclerView.Adapter<rcvAdapter.StoreReviewHolder>() {

    inner class StoreReviewHolder(binding: ReviewListBinding) : ViewHolder(binding.root) {
        var review = binding.reviewTV
        var rate = binding.rateTV

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreReviewHolder {
        val binding: ReviewListBinding =
            ReviewListBinding.inflate(LayoutInflater.from(parent.context))
        return StoreReviewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreReviewHolder, position: Int) {
        holder.review.text = reviews[position].content
        holder.rate.text = "${reviews[position].score.toString()}점"
        holder.itemView.setOnClickListener() {
            var intent = Intent(context, StoreReviewEditActivity::class.java)

            intent.apply {
                Log.d(TAG, "onBindViewHolder: 확인확인${reviews[position].content}")
                putExtra("position",position)
            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

}
