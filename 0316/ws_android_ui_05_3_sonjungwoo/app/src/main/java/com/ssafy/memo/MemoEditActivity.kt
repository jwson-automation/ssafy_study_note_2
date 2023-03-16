package com.ssafy.memo

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.ssafy.memo.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoEditBinding
    var mgr = MemoItemMgr
    private var isUpdate = false
    private var idx = -1
    val currentTime = "2023-03-16"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoEditBinding.inflate(layoutInflater)

        setContentView(binding.root)


        idx = intent.getIntExtra("idx", -1)
        isUpdate = idx != -1

        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")
        var intent = Intent()


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

        binding.saveButton.setOnClickListener {
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
                mgr.add(
                    MemoItem(
                        todo.toString(),
                        description.toString(),
                        currentTime.toString()
                    )
                )
                setResult(Activity.RESULT_OK,intent)
                finish()
            } else {
                Toast.makeText(this, "입력값을 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.updateButton.setOnClickListener {
            var todo = binding.toDoListInput.text
            var description = binding.descriptionInput.text

            if (description.isNotEmpty()) {
                mgr.update(
                    idx,
                    MemoItem(
                        todo.toString(),
                        description.toString(),
                        currentTime.toString()
                    )
                )
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

        binding.cancelButton.setOnClickListener {
            finish()
        }


    }


}