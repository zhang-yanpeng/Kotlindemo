package com.zyp.kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zyp.kotlin.R
import com.zyp.kotlin.adapter.TextStringAdapter
import kotlinx.android.synthetic.main.activity_recycleview.*

class RecycleviewActivity : AppCompatActivity() {

    var data: ArrayList<String> = ArrayList()
    var textAdapter:TextStringAdapter = TextStringAdapter(this,data)
    var layoutManager:LinearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview)

        var i = 0
        while (i < 10) {
            data.add("111")
            data.add("222")
            data.add("333")
            i++
        }

        textRecycle.adapter = textAdapter
        textRecycle.layoutManager = layoutManager
        textAdapter.notifyDataSetChanged()

    }
}
