package com.ssafy.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MemoListAdapter (context: Context, private val items: List<MemoItem>, private val resource: Int) :
    ArrayAdapter<MemoItem>(context, resource, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resource, null)
        val textView = view.findViewById<TextView>(R.id.memoTitle)
        val memo = items[position]
        val content = "${memo.content} ${memo.date}"
        textView.text = content
        return view
    }
}