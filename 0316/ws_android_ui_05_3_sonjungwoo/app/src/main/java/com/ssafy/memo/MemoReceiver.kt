package com.ssafy.memo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MemoReceiver : BroadcastReceiver()  {
    private val TAG = this.javaClass.simpleName
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d(TAG, "Received intent : $p1")
        Toast.makeText(p0, p1?.getStringExtra("title").toString(),Toast.LENGTH_SHORT).show()
    }
}