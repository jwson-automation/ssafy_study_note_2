package com.ssafy.ws_android_network_01_2

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationClass : Application() {
    // ipconfig 를 통해 IP 확인하기
    // 핸드폰으로 접속은 같은 인터넷으로 연결 되어있어야함 (유,무선)
    val SERVER_URL = "http://172.30.1.63:8080/"

    companion object {
        // 전역변수 문법을 통해 Retrofit 인스턴스를 앱 실행시 1 번만 생성하여 사용 (Singleton)
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        // 앱이 처음 생성되는 순간, retrofit 인스턴스를 생성
        retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}