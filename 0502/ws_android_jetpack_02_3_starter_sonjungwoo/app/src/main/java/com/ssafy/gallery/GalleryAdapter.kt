package com.ssafy.gallery

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.gallery.database.Photo
import com.ssafy.gallery.databinding.ItemLayoutBinding

private const val TAG = "GalleryAdapter_싸피"
class GalleryAdapter(context :Context, private val photos: MutableList<Photo>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private val mContext: Context = context

    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindinfo(position: Int) {
            val ImgV: ImageView = binding.imgV
            binding.photo = photos[position]
            binding.root.setOnClickListener{
                Log.d(TAG, "bindinfo: $position")
                (mContext as MainActivity).changeFragment(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //item을 viewGroup에 붙여줬습니다.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bindinfo(position)
        }
    }

}

@BindingAdapter("imageUrl")
fun loadImage(view:ImageView, src:String){
    val packageName = view.context.packageName
    val redId = view.resources.getIdentifier(src,"drawable",packageName)
    view.setImageResource(redId)
}