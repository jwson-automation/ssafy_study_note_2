package com.ssafy.gallery

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ssafy.gallery.database.Database
import com.ssafy.gallery.util.StaticMemory
import com.ssafy.gallery.database.Photo
import com.ssafy.gallery.util.StaticMemory.DATABASE_TABLE
import com.ssafy.gallery.util.StaticMemory.PHOTO_DATE
import com.ssafy.gallery.util.StaticMemory.PHOTO_LOCATION
import com.ssafy.gallery.util.StaticMemory.PHOTO_SRC

class GalleryRepository private constructor(context: Context) {
    val callback = object : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            val now = System.currentTimeMillis()
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/apple');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/beach');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/bigben');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/bird');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/blueberries');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/city');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/cornflower');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/cute');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/dubai');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/duckling');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/fantasy');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/jellyfish');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/milkyway');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/mountain');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/mountains');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/nature');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/nuthatch');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/papenburg');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/rhododendron');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/sea');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/sky');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/thunderstorm');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/tree');")
            db.execSQL("insert into $DATABASE_TABLE ( $PHOTO_LOCATION, $PHOTO_DATE, $PHOTO_SRC) values ('구미시 공단동', datetime('now'), '@drawable/woman');")
        }
    }
    private val database: Database = Room.databaseBuilder(
        context.applicationContext,
        Database::class.java,
        StaticMemory.DATABASE_TABLE
    ).addCallback(callback).build()



    private val dao = database.GalleryDao()

    suspend fun selectPhotos(num: Int): Photo {
        return dao.selectPhotos(num)

    }

    suspend fun selectAllPhotos(): MutableList<Photo> {
        return dao.selectAllPhotos()
    }

    companion object {
        private var INSTANCE: GalleryRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GalleryRepository(context)
            }
        }

        fun get(): GalleryRepository {
            return INSTANCE ?: throw IllegalStateException("Repository must be initialized")
        }
    }
}