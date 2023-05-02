package com.ssafy.gallery

import android.app.Application

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        GalleryRepository.initialize(this)
    }
}