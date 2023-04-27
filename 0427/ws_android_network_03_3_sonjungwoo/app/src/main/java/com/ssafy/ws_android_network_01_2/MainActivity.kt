package com.ssafy.ws_android_network_01_2

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var nAdapter: NfcAdapter
    private lateinit var pIntent: PendingIntent
    private lateinit var filters: Array<IntentFilter>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDAO()
        initNFC()

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
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ${intent.hashCode()}")
        nAdapter.enableForegroundDispatch(this, pIntent, filters, null)
    }

    override fun onPause() {
        super.onPause()
        nAdapter.disableForegroundDispatch(this)
        Log.d(TAG, "onPause: ${intent.hashCode()}")
    }

    private fun initNFC(){
        nAdapter = NfcAdapter.getDefaultAdapter(this)
        val i = Intent(this, StoreReviewActivity::class.java) //자기자신으로 다시 호출. MainActivity::class.java == javaClass
        i.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        pIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_MUTABLE)
        val ndef_filter = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        filters = arrayOf(ndef_filter)
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