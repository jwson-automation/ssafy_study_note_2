package com.ssafy.memo

import android.app.AlarmManager
import android.app.PendingIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.core.view.isVisible
import com.ssafy.memo.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoEditBinding
    var mgr = MemoItemMgr
    private var isUpdate = false
    private var idx = -1
    val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_MUTABLE)
    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoEditBinding.inflate(layoutInflater)

        setContentView(binding.root)


        idx = intent.getIntExtra("idx", -1)
        isUpdate = idx != -1

        if (isUpdate) {
            binding.saveButton.isVisible = false
        }

            binding.saveButton.setOnClickListener {
                var todo = binding.toDoListInput.text
                var description = binding.descriptionInput.text
//                val currentTime = System.currentTimeMillis()
                val currentTime = "2023-03-16"

                if (todo.isNotEmpty() && description.isNotEmpty()) {
                    mgr.add(
                        MemoItem(
                            todo.toString(),
                            description.toString(),
                            currentTime.toString()
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(this, "입력값을 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }

            binding.cancelButton.setOnClickListener {
                finish()
            }

        binding.minute10Alarm.setOnCheckedChangeListener{
            var triggerTime = (SystemClock.elapsedRealtime() + 5 * 1000) // 5초
            alarmManager.set(AlarmManager.ELAPSED_REALTIME, triggerTime, pendingIntent)
        }

        binding.minute20Alarm.setOnCheckedChangeListener(){
            var triggerTime = (SystemClock.elapsedRealtime() + 10 * 1000) // 5초
            alarmManager.set(AlarmManager.ELAPSED_REALTIME, triggerTime, pendingIntent)
        }

        }




    }