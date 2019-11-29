package com.river.kotlindemo.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.GestureDetector
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

import com.river.kotlindemo.R

/**
 * 用于实现全局悬浮按钮
 * Created by zhangyanpeng on 2019/11/11
 */
class WindowFloatService : Service() {

    private var wManager: WindowManager? = null
    private var lParams: WindowManager.LayoutParams? = null
    private var inflater: LayoutInflater? = null
    private var floatLayout: FrameLayout? = null
    private var ivFloat: ImageView? = null
    internal lateinit var myOnGestureListener: GestureDetector
    internal var isMove = false

    private var binder: MyBinder = MyBinder()
//  判断当前悬浮按钮是否存在
    private var isShow = true

    val layoutParams: WindowManager.LayoutParams
        get() {
            val params = WindowManager.LayoutParams()
            params.gravity = Gravity.LEFT or Gravity.TOP
            params.x = 0
            params.y = 0
            params.flags = (WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR or
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH)
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
            }
            return params
        }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    class MyBinder : Binder() {
        open fun getService(): WindowFloatService {
            return WindowFloatService()
        }
    }

    override fun onCreate() {
        super.onCreate()
        creatWindowView()
        addWindowView()
    }

    private fun creatWindowView() {

        wManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        lParams = layoutParams
        inflater = LayoutInflater.from(this)
        floatLayout = inflater!!.inflate(R.layout.window_float_layout, null) as FrameLayout
        ivFloat = floatLayout!!.findViewById(R.id.iv_float)
        floatLayout!!.setOnTouchListener(FloatListener())
    }


    fun addWindowView() {
        Log.i("kotlin", "addWindowView ${wManager}")
        if (wManager == null) return

        wManager!!.addView(floatLayout, lParams)
        isShow = true
    }

    fun removeWindowView() {
        Log.i("kotlin", "removeWindowView ${wManager}")
        wManager!!.removeView(floatLayout)
        isShow = false
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        removeWindowView()
    }

    /**
     * 滑动监听
     */
    inner class FloatListener : View.OnTouchListener {
         var mTouchStartX: Int = 0
         var mTouchStartY: Int = 0
         var mStartX: Int = 0
         var mStartY: Int = 0
         var mTouchCurrentX: Int = 0
         var mTouchCurrentY: Int = 0
         var mStopX: Int = 0
         var mStopY: Int = 0

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    isMove = false
                    mStartX = event.x.toInt()
                    mStartY = event.y.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    mTouchCurrentX = event.rawX.toInt()
                    mTouchCurrentY = event.rawY.toInt()
                    lParams!!.x += mTouchCurrentX - mStartX
                    lParams!!.y += mTouchCurrentY - mStartY
                    wManager!!.updateViewLayout(floatLayout, lParams)

                    mStartX = mTouchCurrentX
                    mStartY = mTouchCurrentY
                }
                MotionEvent.ACTION_UP -> {
                    mStopX = event.x.toInt()
                    mStopY = event.y.toInt()

                    if (Math.abs(mStartX - mStopX) >= 1 || Math.abs(mStartY - mStopY) >= 1) {
                        isMove = true
                    }
                }
            }

            return true
        }
    }

}
