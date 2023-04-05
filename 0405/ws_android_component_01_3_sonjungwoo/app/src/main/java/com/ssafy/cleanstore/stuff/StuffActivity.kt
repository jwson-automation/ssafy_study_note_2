package com.ssafy.cleanstore.stuff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.cleanstore.adapter.StuffAdapter
import com.ssafy.cleanstore.databinding.ActivityStuffBinding
import com.ssafy.cleanstore.db.TmpStuff
import com.ssafy.cleanstore.dto.Stuff

class StuffActivity : AppCompatActivity() {
    lateinit var binding: ActivityStuffBinding
    lateinit var adapter: StuffAdapter
    var stuffs = TmpStuff.Stuffs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStuffBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDummyData()
        initAdapter()
        initButton()
    }

    override fun onResume() {
        super.onResume()
        initAdapter()
    } // 이걸 대체할 수 잇는 친구가 있을거임

    private fun setDummyData(){
        stuffs.add(Stuff("정우1","10"))
        stuffs.add(Stuff("정우2","11"))
        stuffs.add(Stuff("정우3","12"))
    }

    private fun initAdapter(){
        adapter = StuffAdapter(this,stuffs)
        var pdv = binding.productListView

        pdv.adapter = adapter
        pdv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun initButton(){
        var registBtn = binding.registBtn

        registBtn.setOnClickListener(){
            var intent = Intent(this,StuffEditActivity::class.java)
            startActivity(intent)
        }
    }
}