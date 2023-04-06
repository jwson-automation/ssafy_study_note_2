package com.ssafy.cleanstore.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ssafy.cleanstore.dto.Stuff

private const val TAG = "StuffDao_싸피"
class StuffDao(val mCtx: Context) {
    lateinit var helper: stuffDBHelper
    lateinit var sqlDB: SQLiteDatabase
//    private var mCtx: Context? = null
    private val DB_NAME = "clean_store"
    private val TABLE_NAME = "Stuff"
    private val STUFF_ID = "_id"
    private val STUFF_NAME = "name"
    private val STUFF_CNT = "count"

    // 오픈에서 문제가 발생했는데 그 이유가 context를 제대로 전달해주지 않아서였음

    fun open() {
        helper = stuffDBHelper(mCtx!!)
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
    fun insert(name: String, count: String) {
        helper.insert(name, count)
    }

    // 특정 물품 조회 method
    fun select(id: Int) {
        helper.select(id)
    }

    // 물품 조회 method
    fun selectAll(): ArrayList<Stuff> {
        return helper.selectAll()
    }

    // 물품정보 변경
    fun update() {

    }

    // 물품 삭제 method
    fun delete(id: Int) {
        helper.delete(id)
    }

    inner class stuffDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
        private lateinit var db: SQLiteDatabase

        override fun onOpen(db: SQLiteDatabase?) {
            super.onOpen(db)
            this.db = db!!
        }

        // 테이블 생성
        override fun onCreate(db: SQLiteDatabase?) {
            val query: String =
                "CREATE TABLE if not exists $TABLE_NAME ( $STUFF_ID integer primary key autoincrement, $STUFF_NAME text, $STUFF_CNT text);"
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

        fun selectAll(): ArrayList<Stuff> {
            var result = ArrayList<Stuff>()
            db.rawQuery("select * from $TABLE_NAME", null).use {
                while (it.moveToNext()) {
                    result.add(Stuff(it.getInt(0), it.getString(1), it.getString(2)))
                }
            }
            return result
        }

        fun select(id: Int): Stuff {
            val columns = arrayOf("_id", "txt")
            val cursor =
                db.query(TABLE_NAME, columns, "_id=?", arrayOf(id.toString()), null, null, null)
            var result = Stuff(0, "", "")
            if (cursor.moveToNext()) {
                result._id = cursor.getInt(0)
                result.name = cursor.getString(1)
                result.count = cursor.getString(2)
            }
            return result
        }

        fun insert(name: String, count: String) {
            // ContentValues를 이용한 저장
            val contentValues = ContentValues()

            //id는 자동생성
            contentValues.put("name", name)
            contentValues.put("count", count)
            db.beginTransaction()
            Log.d(TAG, "insert: 저장")
            val result = db.insert(TABLE_NAME, null, contentValues)
            if (result > 0) {
                Log.d(TAG, "insert: 성공")
                db.setTransactionSuccessful()
                
            }
            db.endTransaction()
        }

        fun update(id: Int, name: String, count: String) {
            // ContentValues를 이용한 수정
            val contentValues = ContentValues()
            contentValues.put("name", name)
            contentValues.put("count", count)
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
