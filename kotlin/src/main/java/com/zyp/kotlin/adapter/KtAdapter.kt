package com.zyp.kotlin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zyp.kotlin.R
import com.zyp.kotlin.bean.People
import kotlinx.android.synthetic.main.item_people.view.*
import java.util.*

/**
 * Created by zhangyanpeng on 2019/12/3
 */
class KtAdapter(var mContext: Context, var mList: ArrayList<People>) : RecyclerView.Adapter<KtAdapter.KTholder>() {

    var inflater: LayoutInflater

    var divideNum = 2

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KTholder {
        var view = inflater.inflate(R.layout.item_people, parent, false)
        var vh = KTholder(view)
        if (viewType == 1000) {
            Log.i("kt", "宽度   ${parent.measuredWidth}      ${parent.width}")
            vh.itemView.layoutParams.width = parent.measuredWidth / divideNum
        }
        return vh
    }

    override fun getItemViewType(position: Int): Int {
        return 1000
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: KTholder, position: Int) {
        holder.age.text = mList.get(position).age
        holder.name.text = mList.get(position).name
        holder.sex.text = mList.get(position).sex
    }

    class KTholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.tv_name
        var age: TextView = itemView.tv_age
        var sex: TextView = itemView.tv_sex
    }
}