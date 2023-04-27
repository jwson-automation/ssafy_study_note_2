package com.ssafy.ws_android_network_01_2.dao

import com.ssafy.ws_android_network_01_2.dto.StoreDTO
import com.ssafy.ws_android_network_01_2.dto.StoreMenuDTO
import com.ssafy.ws_android_network_01_2.dto.StoreReviewDTO

object DB {
    var Store = StoreDTO(0,0.0000,0.0000, "null","null")
    var StoreReviews = mutableListOf<StoreReviewDTO>()
    var StoreMenus = mutableListOf<StoreMenuDTO>()
}