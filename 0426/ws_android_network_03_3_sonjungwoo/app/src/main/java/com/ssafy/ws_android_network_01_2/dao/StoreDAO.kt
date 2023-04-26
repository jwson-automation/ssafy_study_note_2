package com.ssaflng.ws_android_network_01_2.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ssafy.ws_android_network_01_2.dto.StoreDTO

private const val TAG = "StoreDAO"

class StoreDAO(val mCtlat: Context) {
    lateinit var helper: StoreDBHelper
    lateinit var sqlDB: SQLiteDatabase
    private val DB_NAME = "clean_store"
    private val TABLE_NAME = "StoreDAO"
    private val StoreDAO_ID = "_id"
    private val StoreDAO_NAME = "name"
    private val StoreDAO_tel = "tel"
    private val StoreDAO_lat = "lat"
    private val StoreDAO_lng = "lng"

    // 오픈에서 문제가 발생했는데 그 이유가 Context를 제대로 전달해주지 않아서였음

    fun open() {
        helper = StoreDBHelper(mCtlat!!)
        sqlDB = helper.writableDatabase
    }

    fun create() {
        helper.onCreate(sqlDB)
    }

    fun upgrade(oldVersion: Int, newVersion: Int) {
        //DB version 변경
        helper.onUpgrade(sqlDB, oldVersion, newVersion)
    }

    fun close() {
        sqlDB.close()
    }

    // 물품 CRUD 구현

    // 물품 등록
    fun insert(name: String, tel: String, lat: Number, lng: Number) {
        helper.insert(name, tel, lat, lng)
    }

    // 특정 물품 조회 method
    fun select(id: Int): StoreDTO? {
        return helper.select(id)
    }

    // 물품 조회 method
    fun selectAll(): ArrayList<StoreDTO> {
        return helper.selectAll()
    }

    // 물품정보 변경
    fun update(id: Number, name: String, count: String, tel: String, lat: Number, lng: Number) {
        helper.update(id, name, count, tel, lat, lng)
    }

    // 물품 삭제 method
    fun delete(id: Int) {
        helper.delete(id)
    }

    inner class StoreDBHelper(Context: Context) : SQLiteOpenHelper(Context, DB_NAME, null, 1) {
        private lateinit var db: SQLiteDatabase

        override fun onOpen(db: SQLiteDatabase?) {
            super.onOpen(db)
            this.db = db!!
        }

        // 테이블 생성
        override fun onCreate(db: SQLiteDatabase?) {
            val querlng: String =
                "CREATE TABLE if not exists $TABLE_NAME ( $StoreDAO_ID integer primary key autoincrement, $StoreDAO_NAME name, $StoreDAO_tel tel, $StoreDAO_lat lat, $StoreDAO_lng lng);"
            db?.execSQL(querlng)
        }

        override fun onUpgrade(
            db: SQLiteDatabase?,
            oldVersion: Int,
            newVersion: Int
        ) { //테이블 삭제 후 생성
            // 구현
            val sql: String = "DROP TABLE if exists $TABLE_NAME"
            db?.execSQL(sql)
            onCreate(db)
        }

        fun selectAll(): ArrayList<StoreDTO> {
            var result = ArrayList<StoreDTO>()
            db.rawQuery("select * from $TABLE_NAME", null).use {
                while (it.moveToNext()) {
                    result.add(
                        StoreDTO(
                            it.getInt(0),
                            it.getDouble(1),
                            it.getDouble(2),
                            it.getString(3),
                            it.getString(4)
                        )
                    )
                }
            }
            return result
        }

        fun select(id: Int): StoreDTO {
            val columns = arrayOf("_id", "name", "tel", "lat", "lng")
            val cursor =
                db.query(TABLE_NAME, columns, "_id=?", arrayOf(id.toString()), null, null, null)
            var result = StoreDTO(0, 0.123, 0.123, "", "")
            if (cursor.moveToNext()) {
                result.id = cursor.getInt(0)
                result.lat = cursor.getDouble(1)
                result.lng = cursor.getDouble(2)
                result.name = cursor.getString(3)
                result.tel = cursor.getString(4)
            }

            Log.d(TAG, "select: $result")
            return result
        }

        fun insert(name: String, tel: String, lat: Number, lng: Number) {
            // ContentValues를 이용한 저장
            val contentValues = ContentValues()

            //id는 자동생성
            contentValues.put("name", name)
            contentValues.put("tel", tel)
            contentValues.put("lat", lat.toDouble())
            contentValues.put("lng", lng.toDouble())

            db.beginTransaction()
            Log.d(TAG, "insert: 저장")
            val result = db.insert(TABLE_NAME, null, contentValues)
            if (result > 0) {
                Log.d(TAG, "insert: 성공")
                db.setTransactionSuccessful()

            }
            db.endTransaction()
        }

        fun update(id: Number, name: String, count: String, tel: String, lat: Number, lng: Number) {
            // ContentValues를 이용한 수정
            val contentValues = ContentValues()
            contentValues.put("name", name)
            contentValues.put("count", count)
            contentValues.put("tel", tel)
            contentValues.put("lat", lat.toDouble())
            contentValues.put("lng", lng.toDouble())

            db.beginTransaction()
            val result = db.update(TABLE_NAME, contentValues, "_id=?", arrayOf(id.toString()))
            if (result > 0) {
                db.setTransactionSuccessful()
            }
            db.endTransaction()
        }

        fun delete(id: Int) {
            db.beginTransaction()
            val result = db.delete(TABLE_NAME, "_id=?", arrayOf(id.toString()))
            if (result > 0) {
                db.setTransactionSuccessful()
            }
            db.endTransaction()
        }
    }

}