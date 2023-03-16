package com.ssafy.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MemoAdapter
    private var mgr = MemoItemMgr

    // 업데이트, 등록
//    private val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//        if(it.resultCode == RESULT_OK) {
//            val data = it.data
//
//            data?.let {
//                it.getStringExtra("content")
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mgr.add(MemoItem("메모 앱 만들기1", "test1", "2023-03-15"))

        initAdapter()
    }

    private fun initAdapter(){
        adapter = MemoAdapter(this, mgr.getList())
        binding.memoListView.adapter = adapter
        binding.memoListView.setOnItemClickListener { adapterView, view, i, l ->
            val memo = adapter.getItem(i).toString()
            val intent = Intent(this, MemoEditActivity::class.java)

            intent.putExtra("idx", i)
            intent.putExtra("memo",memo)

            startActivity(intent)
//            register.launch(intent)
            onResume()
        }
        registerForContextMenu(binding.memoListView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.addButton -> {
                val intent = Intent(this, MemoEditActivity::class.java)
                intent.putExtra("isUpdate", false)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        if(::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        }
    }



    }

