package com.ssafy.memo

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.memo.databinding.ItemListviewBinding

class MemoAdapter(val context: Context, val memoList : ArrayList<MemoDto>) : RecyclerView.Adapter<MemoAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(binding: ItemListviewBinding) : RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener{
        var title = binding.itemA
        var content = binding.itemB
        var date = binding.itemC
        override fun onCreateContextMenu(
            p0: ContextMenu?,
            p1: View?,
            p2: ContextMenu.ContextMenuInfo?
        ) {
            p1?.setOnClickListener{
                itemClickListener.onClick(layoutPosition)
            }
        }
    }

    lateinit var itemClickListener:ItemClickListener

    interface ItemClickListener{
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoAdapter.CustomViewHolder {
        val binding: ItemListviewBinding = ItemListviewBinding.inflate(LayoutInflater.from(parent.context))
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoAdapter.CustomViewHolder, position: Int) {
        holder.title.text = memoList[position].title
        holder.content.text = memoList[position].content
        holder.date.text = memoList[position].date
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}