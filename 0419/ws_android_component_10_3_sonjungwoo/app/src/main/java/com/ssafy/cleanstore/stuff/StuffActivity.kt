package com.ssafy.cleanstore.stuff

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.cleanstore.BoundService
import com.ssafy.cleanstore.adapter.StuffAdapter
import com.ssafy.cleanstore.databinding.ActivityStuffBinding
import com.ssafy.cleanstore.dto.Stuff

private const val TAG = "StuffActivity_싸피"
class StuffActivity : AppCompatActivity() {
    lateinit var binding: ActivityStuffBinding
    lateinit var adapter: StuffAdapter
    lateinit var mService : BoundService
    lateinit var stuffs : ArrayList<Stuff>
    private var isBound = false

    val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as BoundService.MyLocalBinder
            mService = binder.getService()
            isBound = true
            Log.d(TAG, "onServiceConnected: 서비스가 연결되었습니다.")
            initAdapter()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
            Log.d(TAG, "onServiceDisconnected: 서비스가 연결되지 않았습니다.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStuffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //서비스 연결
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
        initButton()
    }

    override fun onResume() {
        super.onResume()
        // 다시 돌아오면 다시 연결해줍니다. ( 이때 리스트를 불러옴 )
        unbindService(connection)
        bindService(Intent(this, BoundService::class.java), connection, Context.BIND_AUTO_CREATE)
    }

    private fun initAdapter(){
        stuffs = mService.selectAll()
        adapter = StuffAdapter(this,stuffs)
        var pdv = binding.productListView

        pdv.adapter = adapter
        pdv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun initButton(){
        var registBtn = binding.registBtn
        var drawerBtn = binding.drawerBtn

        registBtn.setOnClickListener(){
            var intent = Intent(this,StuffEditActivity::class.java)
            startActivity(intent)
        }


        var isOpen = false
        drawerBtn.setOnClickListener(){
            if (isOpen){
                binding.drawerlayout.closeMenu()
                isOpen = false
            }else{
                binding.drawerlayout.openMenu()
                isOpen = true
            }

        }
    }
}