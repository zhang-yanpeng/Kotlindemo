package com.zyp.kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zyp.kotlin.R

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
    }
}
