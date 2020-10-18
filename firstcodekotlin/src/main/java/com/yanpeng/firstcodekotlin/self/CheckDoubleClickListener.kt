package com.yanpeng.firstcodekotlin.self

import android.view.View

class CheckDoubleClickListener:View.OnClickListener {

    var lastClickTime=0

    override fun onClick(p0: View?) {
        System.currentTimeMillis()
    }
}