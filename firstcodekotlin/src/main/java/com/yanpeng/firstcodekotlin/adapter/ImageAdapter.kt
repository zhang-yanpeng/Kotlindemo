package com.yanpeng.firstcodekotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yanpeng.firstcodekotlin.R
import java.io.File


class ImageAdapter(var list: ArrayList<File>) : RecyclerView.Adapter<ImageAdapter.ImageVH>() {

    class ImageVH(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageVH(view)
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        Glide.with(holder.itemView).load(list[position]).into(holder.image)
    }

    override fun getItemCount() = if (list == null) 0 else list.size
}