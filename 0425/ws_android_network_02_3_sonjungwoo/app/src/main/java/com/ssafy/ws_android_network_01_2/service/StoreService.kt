package com.ssafy.ws_android_network_01_2.service

import com.ssafy.ws_android_network_01_2.dto.StoreDTO
import com.ssafy.ws_android_network_01_2.dto.StoreReviewDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface StoreService {
    @GET("store/{storeId}")
    suspend fun selectStore(@Path("storeId") storeId:String): Response<StoreDTO>

    @GET("store/{storeId}/reviews")
    suspend fun selectStoreReviews(@Path("storeId") storeId:String): Response<MutableList<StoreReviewDTO>>
}