package com.river.kotlindemo.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.river.kotlindemo.R
import com.river.kotlindemo.utils.Boy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


//  使用char构建字符串
    val aString:String = String(charArrayOf(' ','a'))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setText("123")

        btn1.text = "你没想到吧"

        btn1.setOnClickListener {
            btn1.setText("点了一下")

            Toast(this)
//            toast()


        }

        btn1.setOnLongClickListener {
            btn1.text = "长按"
            btn1.setText("你长按了一下")
             true
        }
    }

    fun getName():String?{
//        return "xiaoming"
        return null
    }

    fun getValue(){
        println("这是getValue（）")
    }
}
