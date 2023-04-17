package com.ssafy.cleanstore.dto

import java.io.Serializable

class Stuff : Serializable {
    var _id : Int
    var name : String
    var count : String
    var regDate : String

    constructor(id:Int, name:String, count:String, regDate:String){
        this._id = id
        this.name = name
        this.count = count
        this.regDate = regDate
    }

    override fun toString(): String {
        return "물품 : $name -> 수량 : ${count}개, 입고일 : $regDate"
    }

}