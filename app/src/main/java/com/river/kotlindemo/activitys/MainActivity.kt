package com.river.kotlindemo.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.river.kotlindemo.R
import com.river.kotlindemo.utils.Boy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //  使用char构建字符串
    val aString: String = String(charArrayOf(' ', 'a'))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun getName(): String? {
//        return "xiaoming"
        return null
    }

    fun getValue() {
        println("这是getValue()")
    }
}
