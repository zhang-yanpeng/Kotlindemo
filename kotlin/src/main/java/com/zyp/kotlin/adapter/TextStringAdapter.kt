package com.zyp.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zyp.kotlin.R
import kotlinx.android.synthetic.main.item_text.view.*

/**
 * Created by zhangyanpeng on 2020/3/3
 */
class TextStringAdapter(var mcontext:Context,var datas:ArrayList<String>):RecyclerView.Adapter<TextStringAdapter.TextVH>() {

//    var inflater:LayoutInflater = LayoutInflater.from(mcontext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextVH {
        return TextVH(LayoutInflater.from(mcontext).inflate(R.layout.item_text,parent,false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: TextVH, position: Int) {
        holder.text.text=datas.get(position)
    }



    class TextVH(itemview : View) :RecyclerView.ViewHolder(itemview){
        var text:TextView = itemview.tv_item_text
    }
}