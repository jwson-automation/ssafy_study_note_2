package com.ssafy.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MemoAdapter
    private var mgr = MemoItemMgr

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
            val memo = adapterView.adapter.getItem(i) as MemoItem
            val intent = Intent(this, MemoEditActivity::class.java)

            intent.putExtra("idx", i)
            intent.putExtra("title",memo.title.toString())
            intent.putExtra("content", memo.content.toString())

            startActivity(intent)
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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        mgr.remove(info.position)
        adapter.notifyDataSetChanged()

        return super.onContextItemSelected(item)
    }

    }

