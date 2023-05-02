package com.ssafy.gallery.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Photo::class],version = 1)
abstract class Database : RoomDatabase() {
    abstract fun GalleryDao() : GalleryDao
}