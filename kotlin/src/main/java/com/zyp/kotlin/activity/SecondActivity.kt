package com.zyp.kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zyp.kotlin.R
import com.zyp.kotlin.adapter.KtAdapter
import com.zyp.kotlin.bean.People
import kotlinx.android.synthetic.main.activity_second.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class SecondActivity : AppCompatActivity() {
    var peoples: ArrayList<People> = ArrayList()
    lateinit var adapter: KtAdapter
    var layoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        peoples.add(People("张三四", "20", "男"))
        peoples.add(People("张三", "20", "男"))
        peoples.add(People("张三四", "20", "男"))
        peoples.add(People("张三", "20", "男"))
        peoples.add(People("张三四", "20", "男"))
        peoples.add(People("张三", "20", "男"))

        adapter= KtAdapter(this, peoples)
        kt_recycle.adapter = adapter
        kt_recycle.layoutManager = layoutManager
        adapter.notifyDataSetChanged()

        tv_add.onClick {
            addItem()
        }

        tv_del.onClick {
            delItem()
        }

    }

    fun addItem() {
        peoples.add(People("lala", "18", "男"))
        adapter.notifyDataSetChanged()
    }

    fun delItem(){
        if (peoples.size==0){
            toast("没有数据了")
        }else{
            peoples.removeAt(0)
            adapter.notifyDataSetChanged()
        }
    }
}
