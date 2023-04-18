package com.ssafy.cleanstore

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.ssafy.cleanstore.databinding.DialogLoadingBinding
import com.ssafy.cleanstore.stuff.StuffActivity

private const val TAG = "LoadingDialog_싸피"
class LoadingDialog(context: Context) : Dialog(context){
    lateinit var binding: DialogLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable())
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: 멈췄습니다.")
    }
    

}