package com.ssafy.cleanstore

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import com.ssafy.cleanstore.databinding.DialogLoadingBinding
import com.ssafy.cleanstore.stuff.StuffActivity

class LoadingDialog(context: Context) : Dialog(context){
    lateinit var binding: DialogLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable())

        val handler = Handler()

        handler.postDelayed({
            val activity = context

            var intent = Intent(activity, StuffActivity::class.java)
            startActivity(activity, intent, Bundle())
        }, 10000)

    }

}