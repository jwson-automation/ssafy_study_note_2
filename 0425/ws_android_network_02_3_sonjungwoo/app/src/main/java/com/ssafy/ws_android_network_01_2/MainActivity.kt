package com.ssafy.ws_android_network_01_2

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.ssaflng.ws_android_network_01_2.dao.StoreDAO
import com.ssafy.ws_android_network_01_2.dao.DB
import com.ssafy.ws_android_network_01_2.databinding.ActivityMainBinding
import com.ssafy.ws_android_network_01_2.dto.StoreDTO
import com.ssafy.ws_android_network_01_2.service.StoreService
import com.ssafy.ws_android_network_01_2.storeReview.StoreReviewActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity_싸피"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val storeDao = StoreDAO(this)
    private val STORE_ID = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDAO()

        val storeInterface = ApplicationClass.retrofit.create(StoreService::class.java)

        binding.Btn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val response = storeInterface.selectStore("1")
                if (response.isSuccessful) {
                    DB.Store = StoreDTO(
                        response.body()?.id ?: 0,
                        response.body()?.lat ?: 0.0,
                        response.body()?.lng ?: 0.0,
                        response.body()?.name ?: "",
                        response.body()?.tel ?: ""
                    )
                    setStoreTV(DB.Store)
                }

            }
        }
    }

    fun initDAO() {
        storeDao.open()
    }

    private fun setStoreTV(storeDTO: StoreDTO) {
        Log.d(TAG, "setStoreTV: $storeDTO")

        binding.nameTV.text = storeDTO.name
        binding.phoneTV.text = storeDTO.tel
        binding.xTV.text = storeDTO.lat.toString()
        binding.yTV.text = storeDTO.lng.toString()
        binding.progressStatus.text = "불러오기 완료"

        binding.nameTV.setOnClickListener{
            val intent = Intent(this,StoreReviewActivity::class.java)
            this.startActivity(intent)
        }
    }


}