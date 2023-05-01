package com.ssafy.gallery

data class Photo(var location : String, var date : String, var src: String) {
    var num = -1

    constructor(_num:Int, location: String, date: String, src: String): this(location, date, src){
        num = _num
    }
}