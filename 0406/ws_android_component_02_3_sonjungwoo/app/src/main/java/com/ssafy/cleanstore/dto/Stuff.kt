package com.ssafy.cleanstore.dto

import java.io.Serializable

class Stuff : Serializable {
    var _id : Int
    var name : String
    var count : String

    constructor(id:Int, name:String, count:String){
        this._id = id
        this.name = name
        this.count = count
    }

    override fun toString(): String {
        return super.toString()
    }

}