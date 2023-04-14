package com.ssafy.cleanstore.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.cleanstore.databinding.StuffListBinding
import com.ssafy.cleanstore.dto.Stuff
import com.ssafy.cleanstore.stuff.StuffEditActivity

private const val TAG = "StuffAdapter_싸피"
class StuffAdapter (val context: Context, val stuffs : ArrayList<Stuff>) : RecyclerView.Adapter<StuffAdapter.StuffViewHolder>(){

    inner class StuffViewHolder(binding: StuffListBinding) : RecyclerView.ViewHolder(binding.root){
        var name = binding.name
        var count = binding.count
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StuffViewHolder {
        val binding:StuffListBinding = StuffListBinding.inflate(LayoutInflater.from(parent.context))
        return StuffViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StuffViewHolder, position: Int) {
        holder.name.text = stuffs[position].name
        holder.count.text = stuffs[position].count
        holder.itemView.setOnClickListener(){
            var intent = Intent(context, StuffEditActivity::class.java)

            intent.apply {
                putExtra("id", stuffs[position]._id)
                putExtra("name", stuffs[position].name)
                putExtra("count", stuffs[position].count)
            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return stuffs.size
    }

}