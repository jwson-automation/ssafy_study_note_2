package com.ssafy.ws_android_network_01_2.service

import android.telecom.Call
import com.ssafy.ws_android_network_01_2.dto.StoreMenuDTO
import com.ssafy.ws_android_network_01_2.dto.StoreReviewDTO
import retrofit2.Response
import retrofit2.http.*

interface StoreMenuService {
    @GET("store/menu/{menuId}")
    suspend fun selectMenu(@Path("menuId") id: String): Response<StoreMenuDTO>

}