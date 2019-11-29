package com.river.kotlindemo.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.river.kotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn1.setOnClickListener { toSecondActivity() }
        btn2.setOnClickListener { toSecondActivity() }
    }

    fun toSecondActivity() {
        var it = Intent()
        it.setClass(this, SecondActivity::class.java)
        startActivity(it)
    }


}
