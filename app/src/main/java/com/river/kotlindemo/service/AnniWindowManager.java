package com.river.kotlindemo.service;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.river.kotlindemo.floatWindow.FloatView;

/**
 * 悬浮窗口管理类
 * Created by zhangyanpeng on 2019/11/14
 */
public class AnniWindowManager {

    public static FloatView floatView;

    public static LayoutParams floatParams;

    private static WindowManager windowManager;

    public static void creatFloatWindow(Context context) {
        windowManager = getWindowManager(context);
        if (floatView == null) {
            floatView = new FloatView(context);
            if (floatParams == null) {
                floatParams = new LayoutParams();
                floatParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                floatParams.gravity = Gravity.LEFT | Gravity.TOP;
                floatParams.x = 0;
                floatParams.y = 0;
                floatParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | LayoutParams.FLAG_NOT_FOCUSABLE;

                floatParams.width = LayoutParams.WRAP_CONTENT;
                floatParams.height = LayoutParams.WRAP_CONTENT;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    floatParams.type = LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    floatParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
                }
            }
            floatView.setLayoutParams(floatParams);
            windowManager.addView(floatView, floatParams);
        }
    }

    /**
     * 移除悬浮窗
     * @param context
     */
    public static void removeFloatView(Context context){
        if (floatView!=null){
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(floatView);
            floatView = null;
        }
    }

    /**
     * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
     *
     * @param context 必须为应用程序的Context.
     * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
     */
    private static WindowManager getWindowManager(Context context) {
        if (windowManager == null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    /**
     * 是否有悬浮框
     * @return
     */
    public static boolean hasFloatWindow(){
        return floatView==null?false:true;
    }

}
