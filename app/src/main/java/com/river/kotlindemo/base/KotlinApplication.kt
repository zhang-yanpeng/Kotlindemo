package com.river.kotlindemo.base

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.river.kotlindemo.receivers.WindowShowRecriver

/**
 * Created by zhangyanpeng on 2019/11/12
 */
class KotlinApplication : Application() {

    var num: Int = 0

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivitiyLife())
    }

    //应用进入后台
    override fun onTerminate() {
        super.onTerminate()
        stopService()
    }


    inner class ActivitiyLife : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {

        }

        override fun onActivityResumed(activity: Activity?) {

        }

        override fun onActivityStarted(activity: Activity?) {
            num++
        }

        override fun onActivityDestroyed(activity: Activity?) {

        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

        }

        override fun onActivityStopped(activity: Activity?) {
            num--
//            if (num == 0) {
//                stopService()
//            }
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

        }
    }

    fun startService() {
        var it = Intent("kotlin")
        it.setClass(this, WindowShowRecriver::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            it.setComponent(ComponentName.createRelative(packageName,"com.river.kotlindemo.receivers.WindowShowRecriver"))
            it.setComponent(ComponentName.createRelative(packageName, "com.river.kotlindemo.receivers.WindowShowRecriver"))
        }
        it.putExtra("status", 1)
        sendBroadcast(it)
    }

    fun stopService() {
        var it = Intent("kotlin")
        it.setClass(this, WindowShowRecriver::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            it.setComponent(ComponentName.createRelative(packageName, "com.river.kotlindemo.receivers.WindowShowRecriver"))
        }
        it.putExtra("status", 0)
        sendBroadcast(it)
    }

    fun isAppBack():Boolean{
        val am =getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = am.getRunningTasks(1)
        if (!tasks.isEmpty()) {
            val topActivity = tasks.get(0).topActivity
            if (topActivity.getPackageName() != getPackageName()) {
                return true
            }
        }

        return false
    }
}