package com.river.kotlindemo.activitys

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.Toast
import com.river.kotlindemo.R
import com.river.kotlindemo.floatWindow.FloatScreenService
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_startFloat.setOnClickListener {
            startFloat()
        }

        btn_stopFloat.setOnClickListener { stopFloat() }
    }

    fun stopFloat() {
        //有权限，开启服务
        var ser: Intent = Intent()
        ser.setClass(this, FloatScreenService::class.java)
        stopService(ser)
    }

    fun startFloat() {
//        1 判断权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            var b = Settings.canDrawOverlays(this)
            var b = commonROMPermissionCheck(this)
            if (b) {
                startWindowService()
            } else {
                toSettingPage()
            }
        } else {
            startWindowService()
        }
    }

    var mHandler:Handler = Handler()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mHandler.postDelayed(Runnable {
                    if (commonROMPermissionCheck(this))
                        startWindowService()
                },500)
            }
        }
    }

    fun startWindowService() {
        //有权限，开启服务
        var ser: Intent = Intent()
        ser.setClass(this, FloatScreenService::class.java)
        startService(ser)
    }

    /**
     * 进入设置页面，获取权限
     */
    fun toSettingPage() {
        var settings: Intent = Intent()
        settings.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        settings.setData(Uri.parse("package:" + packageName))
        startActivityForResult(settings, 1)
    }

    /**
     * 判断悬浮窗权限
     */
    private fun commonROMPermissionCheck(context: Context): Boolean {
        var result: Boolean = true
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                val clazz = Settings::class.java
                val canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context::class.java)
//                Settings.canDrawOverlays(context)
                result = canDrawOverlays.invoke(null, context) as Boolean
            } catch (e: Exception) {

            }
        }
        return result
    }

}
