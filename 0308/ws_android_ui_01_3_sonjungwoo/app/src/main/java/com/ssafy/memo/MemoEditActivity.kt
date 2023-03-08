package com.ssafy.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.ssafy.memo.databinding.ActivityMainBinding
import com.ssafy.memo.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.saveButton.setOnClickListener {
            var todo = binding.toDoListInput.text
//            Snackbar.make(it,todo,Snackbar.LENGTH_SHORT).show()
            var description = binding.descriptionInput.text
//            Toast.makeText(this,description, Toast.LENGTH_SHORT).show()

            if (todo.isNotEmpty() && description.isNotEmpty()){
                Toast.makeText(this,todo.toString() + description.toString(), Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"입력값을 확인해주세요", Toast.LENGTH_SHORT).show()
            }


        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}