package com.ssafy.ws_android_network_01_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ssafy.ws_android_network_01_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val storeDao = StoreDAO(this)
    private val STORE_ID = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDAO()

        binding.Btn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                loadStore()
                setStoreTV(getStoreInfo()!!)
            }
        }
    }

    private fun getStoreInfo(): StoreDTO?{
        return storeDao.select(STORE_ID)
    }

    fun initDAO(){
        storeDao.open()
    }

    private suspend fun loadStore(){
        for( i in 1 .. 100) {
            binding.progressBar.setProgress(i)
            binding.progressNumber.text = i.toString()
            delay(10)
        }
    }

    private fun setStoreTV(storeDTO: StoreDTO){
        Log.d(TAG, "setStoreTV: $storeDTO")

        binding.nameTV.text = storeDTO.name
        binding.phoneTV.text = storeDTO.phone
        binding.xTV.text = storeDTO.x.toString()
        binding.yTV.text = storeDTO.y.toString()
        binding.progressStatus.text = "불러오기 완료"
    }


}