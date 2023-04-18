package com.ssafy.cleanstore.stuff

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import com.ssafy.cleanstore.BoundService
import com.ssafy.cleanstore.databinding.ActivityStuffEditBinding
import com.ssafy.cleanstore.db.TmpStuff
import com.ssafy.cleanstore.dto.Stuff
import java.text.DateFormat
import java.util.*
import kotlin.math.log


private const val TAG = "StuffEditActivity_싸피"
class StuffEditActivity : AppCompatActivity() {
    lateinit var binding : ActivityStuffEditBinding
    lateinit var name : String
    lateinit var count : String
    lateinit var regDate : String
    var id = -1
    lateinit var mode : String
    lateinit var mService : BoundService
    private var isBound = false

    var checkIntValue = true

    // 서비스 연결해주는 부분
    val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as BoundService.MyLocalBinder
            mService = binder.getService()
            isBound = true
            Log.d(TAG, "onServiceConnected: 서비스가 연결되었습니다.")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
            Log.d(TAG, "onServiceDisconnected: 서비스가 연결되지 않았습니다.")
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStuffEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        setMode()
        initBtn()
        initCalender()
        initChangedListener()

    }

    private fun initChangedListener(){
        binding.countInput.addTextChangedListener {
            if (checkInt(it.toString())){
                Log.d(TAG, "initChangedListener: ${it.toString()}")
                checkIntValue = true
            }else{
                Log.d(TAG, "initChangedListener: ${it.toString()}")
                binding.countInput.error = "숫자만 입력 가능합니다."
                checkIntValue = false
            }
        }
    }

    private fun initCalender(){
        val calendar = Calendar.getInstance()
        var dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
        var formattedDate = dateFormatter.format(calendar.time)

        binding.regDateInput.text = formattedDate

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 캘린더 인스턴스에 CalendarView 에서 선택한 날짜 세팅
            calendar.set(year, month, dayOfMonth)

            dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
            formattedDate = dateFormatter.format(calendar.time)

            // TextView 에 날짜 세팅하기
            binding.regDateInput.text = formattedDate
        }
    }


    private fun getIntentData(){
        id = intent?.getIntExtra("id", -1)!!
        name = intent?.getStringExtra("name").toString()
        count = intent?.getStringExtra("count").toString()
        regDate = intent?.getStringExtra("regDate").toString()

        Log.d(TAG, "dataCheck: ${id}")
        Log.d(TAG, "dataCheck: ${name}")
        Log.d(TAG, "dataCheck: ${count}")
        Log.d(TAG, "dataCheck: ${regDate}")
    }
    private fun setMode(){
        Log.d(TAG, "setMode: ${id}")
        if (id == -1){
            mode = "CREATE"
            Log.d(TAG, "setMode: CREATE모드")
        }else{
            mode = "UPDATE"
            Log.d(TAG, "setMode: UPDATE모드")
        }
    }

    private fun initBtn(){
        var saveBtn = binding.saveBtn
        var deleteBtn = binding.deleteBtn
        var cancelBtn = binding.cancelBtn
        var updateBtn = binding.updateBtn
//        var nameInp = binding.nameInput
//        var countInp = binding.countInput
        var regDateInp = binding.regDateInput

        if (mode == "CREATE"){
            deleteBtn.visibility = View.GONE
        }else{
            deleteBtn.visibility = View.VISIBLE
        }

        Log.d(TAG, "initBtn: ModeCheck ${mode}")

        if (mode == "UPDATE"){
            saveBtn.visibility = View.GONE
            updateBtn.visibility = View.VISIBLE
            // set Text가 동작하지 않는 이유를 찾아야 합니다.
            binding.nameInput.setText(name)
            Log.d(TAG, "initBtn: UPDATE합니다. ${name}, ${count}")
            binding.countInput.setText(count)
            // set Text가 동작하지 않는 이유를 찾아야 합니다.
            regDateInp.text = regDate

        }else{
            saveBtn.visibility = View.VISIBLE
            updateBtn.visibility = View.GONE
        }


        saveBtn.setOnClickListener(){
            var nameInp = binding.nameInput.text
            var countInp = binding.countInput.text
            var regInp = binding.regDateInput.text

            if(checkIntValue){
            mService.insert(nameInp.toString(),countInp.toString(), regInp.toString())
            finish()
            }
        }
        updateBtn.setOnClickListener(){
            var nameInp = binding.nameInput.text
            var countInp = binding.countInput.text
            var regInp = binding.regDateInput.text

            if(checkIntValue){
            mService.update(id,nameInp.toString(),countInp.toString(), regInp.toString())
            finish()}
        }
        deleteBtn.setOnClickListener(){
            mService.delete(id)
            finish()
        }
        cancelBtn.setOnClickListener(){
         finish()
        }
    }
    private fun checkInt(inp:String) : Boolean{
        return inp.isDigitsOnly()
    }
}
