package com.yanpeng.code

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var view: View
        var sc:ScrollView
        var list:ListView
    }
    
    fun initViewModel() {
        var intent =Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}
