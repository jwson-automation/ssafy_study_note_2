package com.ssafy.cleanstore.dto

import java.io.Serializable

class Stuff : Serializable {
    var name : String
    var count : String

    constructor(name:String, count:String){
        this.name = name
        this.count = count
    }

    override fun toString(): String {
        return super.toString()
    }

}