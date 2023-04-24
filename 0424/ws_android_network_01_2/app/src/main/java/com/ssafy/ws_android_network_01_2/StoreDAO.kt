package com.ssafy.ws_android_network_01_2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

private const val TAG = "StoreDTO_싸피"

class StoreDAO(val mCtx: Context) {
    lateinit var helper: StoreDBHelper
    lateinit var sqlDB: SQLiteDatabase
    private val DB_NAME = "clean_store"
    private val TABLE_NAME = "StoreDAO"
    private val StoreDAO_ID = "_id"
    private val StoreDAO_NAME = "name"
    private val StoreDAO_PHONE = "phone"
    private val StoreDAO_X = "x"
    private val StoreDAO_Y = "y"

    // 오픈에서 문제가 발생했는데 그 이유가 context를 제대로 전달해주지 않아서였음

    fun open() {
        helper = StoreDBHelper(mCtx!!)
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
    fun insert(name: String, phone: String, x: Number, y: Number) {
        helper.insert(name, phone, x, y)
    }

    // 특정 물품 조회 method
    fun select(id: Int) : StoreDTO? {
        return helper.select(id)
    }

    // 물품 조회 method
    fun selectAll(): ArrayList<StoreDTO> {
        return helper.selectAll()
    }

    // 물품정보 변경
    fun update(id: Number, name: String, count: String, phone: String, x: Number, y: Number) {
        helper.update(id, name, count, phone, x, y)
    }

    // 물품 삭제 method
    fun delete(id: Int) {
        helper.delete(id)
    }

    inner class StoreDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
        private lateinit var db: SQLiteDatabase

        override fun onOpen(db: SQLiteDatabase?) {
            super.onOpen(db)
            this.db = db!!
        }

        // 테이블 생성
        override fun onCreate(db: SQLiteDatabase?) {
            val query: String =
                "CREATE TABLE if not exists $TABLE_NAME ( $StoreDAO_ID integer primary key autoincrement, $StoreDAO_NAME text, $StoreDAO_PHONE text, $StoreDAO_X number, $StoreDAO_Y number);"
            db?.execSQL(query)
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
                            it.getString(1),
                            it.getString(2),
                            it.getInt(3),
                            it.getInt(4)
                        )
                    )
                }
            }
            return result
        }

        fun select(id: Int): StoreDTO {
            val columns = arrayOf("_id", "name","phone","x","y")
            val cursor =
                db.query(TABLE_NAME, columns, "_id=?", arrayOf(id.toString()), null, null, null)
            var result = StoreDTO(0, "", "", 0, 0)
            if (cursor.moveToNext()) {
                result._id = cursor.getInt(0)
                result.name = cursor.getString(1)
                result.phone = cursor.getString(2)
                result.x = cursor.getDouble(3)
                result.y = cursor.getDouble(4)
            }

            Log.d(TAG, "select: $result")
            return result
        }

        fun insert(name: String, phone: String, x: Number, y: Number) {
            // ContentValues를 이용한 저장
            val contentValues = ContentValues()

            //id는 자동생성
            contentValues.put("name", name)
            contentValues.put("phone", phone)
            contentValues.put("x", x.toDouble())
            contentValues.put("y", y.toDouble())

            db.beginTransaction()
            Log.d(TAG, "insert: 저장")
            val result = db.insert(TABLE_NAME, null, contentValues)
            if (result > 0) {
                Log.d(TAG, "insert: 성공")
                db.setTransactionSuccessful()

            }
            db.endTransaction()
        }

        fun update(id: Number, name: String, count: String, phone: String, x: Number, y: Number) {
            // ContentValues를 이용한 수정
            val contentValues = ContentValues()
            contentValues.put("name", name)
            contentValues.put("count", count)
            contentValues.put("phone", phone)
            contentValues.put("x", x.toDouble())
            contentValues.put("y", y.toDouble())

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
