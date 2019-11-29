package com.river.kotlindemo.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.river.kotlindemo.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_jump.setOnClickListener {
            var it: Intent = Intent()
            it.setClass(this,MainActivity::class.java)
            startActivity(it)
        }
    }
}
