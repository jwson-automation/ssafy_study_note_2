package com.ssafy.workshop

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ssafy.workshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "jwson"
    lateinit var binding: ActivityMainBinding
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        toolbar.apply {
            inflateMenu(R.menu.menu_main)
        }

        val drawView = findViewById<Draw>(R.id.draw)

        toolbar.setOnMenuItemClickListener(){
            when(it.itemId){
                R.id.m1 -> {Log.d(TAG, "onCreate: m1")
                    drawView.apply {
//                        val paint = Paint()
//                        paint.color = Color.BLACK
                        drawView.currentColor = Color.BLACK
//                        setPaint(paint)  // Draw 뷰에 새로운 Paint 객체를 설정
                        invalidate()
                    }
                }

                R.id.m2 -> {Log.d(TAG, "onCreate: m2")
                    drawView.apply {
//                        val paint = Paint()
//                        paint.color = Color.BLUE
                        drawView.currentColor = Color.BLUE
//                        setPaint(paint)  // Draw 뷰에 새로운 Paint 객체를 설정
                        invalidate()
                    }
                }
                R.id.m3 -> {Log.d(TAG, "onCreate: m3")
                    drawView.apply {
//                        val paint = Paint()
//                        paint.color = Color.RED
                        drawView.currentColor = Color.RED
//                        setPaint(paint)  // Draw 뷰에 새로운 Paint 객체를 설정
                        invalidate()
                    }
                }
                R.id.m4 -> {Log.d(TAG, "onCreate: m4")
                drawView.apply {
                    resetList()
                    invalidate()
                }}
            }
            true
        }
    }
}