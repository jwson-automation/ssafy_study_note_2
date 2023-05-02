package com.ssafy.gallery.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ssafy.gallery.util.StaticMemory


@Entity(tableName = StaticMemory.DATABASE_TABLE)
data class Photo(var photoLocation: String, var photoDate: String, var photoSrc: String) {

    @PrimaryKey(autoGenerate = true)
    var num = 0L

    constructor(_num: Long, photoLocation: String, photoDate: String, photoSrc: String) : this(
        photoLocation,
        photoDate,
        photoSrc
    ) {
        num = _num
    }

}