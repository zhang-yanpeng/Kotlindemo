package com.yanpeng.code

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ListView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var handler:Handler
//        Looper.prepare()
//        Looper.prepareMainLooper()
//        Looper.loop()

        //协程



    }
    
    fun initViewModel() {
        var intent =Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}
