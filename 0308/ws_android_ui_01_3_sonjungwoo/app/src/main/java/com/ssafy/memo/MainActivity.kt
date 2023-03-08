package com.ssafy.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todayMemoSubmitButton.setOnClickListener() {
            val intent = Intent(this, MemoEditActivity::class.java)
            startActivity(intent)
        }

        binding.todayMemoReadButton.setOnClickListener(){
            val intent = Intent(this, MemoInfoActivity::class.java)
            startActivity(intent)
        }
    }
}