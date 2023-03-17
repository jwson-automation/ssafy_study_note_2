package com.ssafy.memo

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.ssafy.memo.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoEditBinding

    // db 추가
    private lateinit var dbHelper: MemoDBHelper
    private lateinit var database: SQLiteDatabase
    //

    var mgr = MemoItemMgr
    var isUpdate = false
    var idx = -1
    val currentTime = "2023-03-17"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = ActivityMemoEditBinding.inflate(layoutInflater)

        //화면 띄우기
        setContentView(binding.root)

        // 어떤 경로로 들어왔는지 검사해서 '저장모드','수정모드'로 분류합니다.
        typeCheck()

        //DB 추가해주기
        dbHelper = MemoDBHelper(this, "new.db", null, 1)
        database = dbHelper.writableDatabase

        // '저장버튼'이 눌렸을 때
        binding.saveButton.setOnClickListener {
            saveButtonMethod()
        }

        // '수정버튼'이 눌렸을 때
        binding.updateButton.setOnClickListener {
            updateButtonMethod()
        }

        // '취소버튼'이 눌렸을 때
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun saveButtonMethod(){
        var todo = binding.toDoListInput.text
        var description = binding.descriptionInput.text

        if (binding.second10.isChecked){
            intent.putExtra("second10", true)
        }else{
            intent.putExtra("second10", false)
        }
        if (binding.second30.isChecked){
            intent.putExtra("second30", true)
        }else{
            intent.putExtra("second30", false)
        }

        if (todo.isNotEmpty() && description.isNotEmpty()) {
            dbHelper.insert(
                    todo.toString(),
                    description.toString(),
                    currentTime.toString()
            )
            //

            intent.putExtra("title", todo.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        } else {
            Toast.makeText(this, "입력값을 확인해주세요", Toast.LENGTH_SHORT).show()

        }
    }

    private fun typeCheck(){
        idx = intent.getIntExtra("idx", -1)
        isUpdate = idx != -1

        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")

        if (isUpdate) {
            binding.saveButton.isVisible = false
            binding.toDoListInput.setText(title)
            binding.toDoListInput.isEnabled = false
            binding.descriptionInput.setText(content)
            binding.dateInput.setText(currentTime)
            binding.dateInput.isEnabled = false
        } else {
            binding.updateButton.isVisible = false
            binding.dateInput.setText(currentTime)
            binding.dateInput.isEnabled = false
        }
    }

    private fun updateButtonMethod(){
        var todo = binding.toDoListInput.text
        var description = binding.descriptionInput.text

        if (description.isNotEmpty()) {

            dbHelper.update(
                idx + 1,
                todo.toString(),
                description.toString(),
                currentTime.toString()
            )
            //

            if (binding.second10.isChecked){
                intent.putExtra("second10", true)
            }else{
                intent.putExtra("second10", false)
            }
            if (binding.second30.isChecked){
                intent.putExtra("second30", true)
            }else{
                intent.putExtra("second30", false)
            }
            intent.putExtra("title", todo.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        } else {
            Toast.makeText(this, "입력값을 확인해주세요", Toast.LENGTH_SHORT).show()
        }
    }

}