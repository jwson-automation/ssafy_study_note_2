package com.ssafy.memo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MemoReceiver : BroadcastReceiver()  {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "알람",Toast.LENGTH_SHORT).show()
    }
}