package com.ssafy.cleanstore

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.ssafy.cleanstore.dao.StuffDao
import com.ssafy.cleanstore.dto.Stuff

private const val TAG = "BoundService_싸피"
class BoundService : Service() {
    var stuffDao = StuffDao(this)

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: 생성됨 ㅋ")
        open()
        create()
    }

    override fun onBind(intent: Intent): IBinder {
        return MyLocalBinder()
    }

    inner class MyLocalBinder() : Binder() {
        fun getService(): BoundService {
            return this@BoundService
        }
    }

    fun open() {
        stuffDao.open()
    }

    fun create() {
        stuffDao.create()
    }

    fun upgrade(oldVersion: Int, newVersion: Int) {
        stuffDao.upgrade(oldVersion, newVersion)
    }

    fun close() {
        stuffDao.close()
    }

    fun insert(name: String, count: String) {
        stuffDao.insert(name, count)
    }

    fun select(id: Int) {
        stuffDao.select(id)
    }

    fun selectAll(): ArrayList<Stuff> {
        return stuffDao.selectAll()
    }

    fun update() {
        stuffDao.update()
    }

    fun delete(id: Int) {
        stuffDao.delete(id)
    }


}