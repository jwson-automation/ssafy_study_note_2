package com.ssafy.cleanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.cleanstore.databinding.ActivityMainBinding
import com.ssafy.cleanstore.stuff.StuffActivity


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var title = binding.title

        title.setOnClickListener(){
            var intent = Intent(this, StuffActivity::class.java)
            startActivity(intent)
        }

    }




}