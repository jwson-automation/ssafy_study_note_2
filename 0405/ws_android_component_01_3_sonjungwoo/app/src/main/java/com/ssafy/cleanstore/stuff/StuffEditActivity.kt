package com.ssafy.cleanstore.stuff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.databinding.ActivityStuffEditBinding
import com.ssafy.cleanstore.db.TmpStuff
import com.ssafy.cleanstore.dto.Stuff

class StuffEditActivity : AppCompatActivity() {
    lateinit var binding : ActivityStuffEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStuffEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBtn()
    }

    private fun initBtn(){
        var saveBtn = binding.saveBtn
        var deleteBtn = binding.deleteBtn
        var cancelBtn = binding.cancelBtn

        saveBtn.setOnClickListener(){
            var nameInp = binding.nameInput.text
            var countInp = binding.nameInput.text

            TmpStuff.Stuffs.add(Stuff(nameInp.toString(),countInp.toString()))
            finish()
        }
        deleteBtn.setOnClickListener(){

        }
        cancelBtn.setOnClickListener(){
         finish()
        }
    }
}