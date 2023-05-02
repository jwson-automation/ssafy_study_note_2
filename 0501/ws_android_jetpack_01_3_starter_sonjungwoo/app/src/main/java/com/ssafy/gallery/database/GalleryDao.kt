package com.ssafy.gallery.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ssafy.gallery.util.StaticMemory

@Dao
interface GalleryDao {

    @Query("Select * FROM ${StaticMemory.DATABASE_TABLE}")
    suspend fun selectAllPhotos(): MutableList<Photo>

    @Query("Select * FROM ${StaticMemory.DATABASE_TABLE} WHERE num = (:num)")
    suspend fun selectPhotos(num: Int): Photo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(dto: Photo)

    @Update
    suspend fun updatePhoto(dto: Photo)

    @Delete
    suspend fun deletePhoto(dto: Photo)

}