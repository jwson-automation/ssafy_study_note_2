package com.ssafy.memo

import java.io.Serializable

class MemoItem : Serializable {
    var title : String
    var content : String
    var date : String

    constructor(title: String, content: String, date: String) {
         this.title = title
         this.content = content
         this.date = date
        }

    override fun toString(): String {
        return title
        }
}