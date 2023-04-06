package com.ssafy.cleanstore.stuff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ssafy.cleanstore.databinding.ActivityStuffEditBinding
import com.ssafy.cleanstore.db.TmpStuff
import com.ssafy.cleanstore.dto.Stuff


private const val TAG = "StuffEditActivity"
class StuffEditActivity : AppCompatActivity() {
    lateinit var binding : ActivityStuffEditBinding
    lateinit var name : String
    lateinit var count : String
    var position = -1
    lateinit var mode : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStuffEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        setMode()
        initBtn()
    }
    private fun getIntentData(){
        position = intent?.getIntExtra("position", -1)!!
        name = intent?.getStringExtra("name").toString()
        count = intent?.getStringExtra("count").toString()

        Log.d(TAG, "dataCheck: ${position}")
        Log.d(TAG, "dataCheck: ${name}")
        Log.d(TAG, "dataCheck: ${count}")
    }
    private fun setMode(){
        if (position == -1){
            mode = "CREATE"
        }else{
            mode = "UPDATE"
        }
    }

    private fun initBtn(){
        var saveBtn = binding.saveBtn
        var deleteBtn = binding.deleteBtn
        var cancelBtn = binding.cancelBtn
        var nameInp = binding.nameInput
        var countInp = binding.countInput

        if (mode == "CREATE"){
            deleteBtn.visibility = View.GONE
        }else{
            deleteBtn.visibility = View.VISIBLE
        }

        if (mode == "UPDATE"){
            saveBtn.visibility = View.GONE
            nameInp.isEnabled = false
            countInp.isEnabled = false
            nameInp.hint = name
            countInp.hint = count

        }else{
            saveBtn.visibility = View.VISIBLE
        }


        saveBtn.setOnClickListener(){
            var nameInp = binding.nameInput.text
            var countInp = binding.nameInput.text

            TmpStuff.Stuffs.add(Stuff(nameInp.toString(),countInp.toString()))
            finish()
        }
        deleteBtn.setOnClickListener(){
            TmpStuff.Stuffs.removeAt(position)
            finish()
        }
        cancelBtn.setOnClickListener(){
         finish()
        }
    }
}