package com.ssafy.ws_android_network_01_2.dao

import com.ssafy.ws_android_network_01_2.dto.StoreDTO
import com.ssafy.ws_android_network_01_2.dto.StoreReviewDTO

object DB {
    var Store = StoreDTO(1,36.123123,139.123123123, "싸피벅스","010-1234-5678")
    var StoreReviews = mutableListOf<StoreReviewDTO>()
}