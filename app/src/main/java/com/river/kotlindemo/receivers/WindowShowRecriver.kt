package com.river.kotlindemo.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.river.kotlindemo.service.WindowFloatService

/**
 * Created by zhangyanpeng on 2019/11/13
 */
class WindowShowRecriver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var a = "2"
        val action = intent!!.action
        val status = intent!!.getIntExtra("status", -1)
        Log.i("kotlin","${action}   ${status}")
        var service:WindowFloatService= WindowFloatService()
        when(status){
            0-> service.removeWindowView()
            1->service.addWindowView()
        }
    }
}