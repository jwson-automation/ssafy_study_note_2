package com.ssafy.memo

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.ssafy.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var MemoItemMgr : IMemoItemMgr = MemoItemMgr()
    private var LIST_MENU : ArrayList<MemoItem> = arrayListOf<MemoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {

        LIST_MENU.add(MemoItem("메모 앱 만들기1", "test1", "2023-03-15"))
        LIST_MENU.add(MemoItem("메모 앱 만들기1", "test1", "2023-03-16"))

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter<MemoItem>(this, R.layout.simple_list_item_1, LIST_MENU)
        binding.memoListView.adapter = adapter

        MemoItemMgr.getList()
    }
}