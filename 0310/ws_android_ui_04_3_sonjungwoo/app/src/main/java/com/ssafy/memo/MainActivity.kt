package com.ssafy.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import com.ssafy.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var MemoItemMgr : IMemoItemMgr = MemoItemMgr()
    private var LIST_MENU : ArrayList<MemoItem> = arrayListOf<MemoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {

        MemoItemMgr.add(MemoItem("메모 앱 만들기1", "test1", "2023-03-15"))
        MemoItemMgr.add(MemoItem("메모 앱 만들기2", "test1", "2023-03-16"))

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initAdapter(){
        val adapter = MemoListAdapter(this, MemoItemMgr.getList(), android.R.layout.simple_list_item_1)
        binding.memoListView.adapter = adapter
    }


    }

