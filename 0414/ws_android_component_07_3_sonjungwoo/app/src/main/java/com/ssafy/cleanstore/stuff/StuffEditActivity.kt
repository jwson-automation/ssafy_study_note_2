package com.ssafy.cleanstore.stuff

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.ssafy.cleanstore.BoundService
import com.ssafy.cleanstore.databinding.ActivityStuffEditBinding
import com.ssafy.cleanstore.db.TmpStuff
import com.ssafy.cleanstore.dto.Stuff


private const val TAG = "StuffEditActivity_싸피"
class StuffEditActivity : AppCompatActivity() {
    lateinit var binding : ActivityStuffEditBinding
    lateinit var name : String
    lateinit var count : String
    var id = -1
    lateinit var mode : String
    lateinit var mService : BoundService
    private var isBound = false

    // 서비스 연결해주는 부분
    val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as BoundService.MyLocalBinder
            mService = binder.getService()
            isBound = true
            Log.d(TAG, "onServiceConnected: 서비스가 연결되었습니다.")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
            Log.d(TAG, "onServiceDisconnected: 서비스가 연결되지 않았습니다.")
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStuffEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        setMode()
        initBtn()
    }


    private fun getIntentData(){
        id = intent?.getIntExtra("id", -1)!!
        name = intent?.getStringExtra("name").toString()
        count = intent?.getStringExtra("count").toString()

        Log.d(TAG, "dataCheck: ${id}")
        Log.d(TAG, "dataCheck: ${name}")
        Log.d(TAG, "dataCheck: ${count}")
    }
    private fun setMode(){
        if (id == -1){
            mode = "CREATE"
            Log.d(TAG, "setMode: CREATE모드")
        }else{
            mode = "UPDATE"
            Log.d(TAG, "setMode: UPDATE모드")
        }
    }

    private fun initBtn(){
        var saveBtn = binding.saveBtn
        var deleteBtn = binding.deleteBtn
        var cancelBtn = binding.cancelBtn
        var updateBtn = binding.updateBtn
        var nameInp = binding.nameInput
        var countInp = binding.countInput

        if (mode == "CREATE"){
            deleteBtn.visibility = View.GONE
        }else{
            deleteBtn.visibility = View.VISIBLE
        }

        if (mode == "UPDATE"){
            saveBtn.visibility = View.GONE
//            nameInp.isEnabled = false
//            countInp.isEnabled = false
            updateBtn.visibility = View.VISIBLE
            nameInp.hint = name
            countInp.hint = count

        }else{
            saveBtn.visibility = View.VISIBLE
            updateBtn.visibility = View.GONE
        }


        saveBtn.setOnClickListener(){
            var nameInp = binding.nameInput.text
            var countInp = binding.countInput.text

            mService.insert(nameInp.toString(),countInp.toString())
            finish()
        }
        updateBtn.setOnClickListener(){
            var nameInp = binding.nameInput.text
            var countInp = binding.countInput.text

            mService.update(id,nameInp.toString(),countInp.toString())
            finish()
        }
        deleteBtn.setOnClickListener(){
            mService.delete(id)
            finish()
        }
        cancelBtn.setOnClickListener(){
         finish()
        }
    }
}