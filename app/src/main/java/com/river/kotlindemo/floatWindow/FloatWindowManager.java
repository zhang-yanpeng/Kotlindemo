package com.river.kotlindemo.floatWindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * 悬浮窗口管理类
 * Created by zhangyanpeng on 2019/11/14
 */
public class FloatWindowManager {

    public static FloatView floatView;

    public static LayoutParams floatParams;

    private static WindowManager windowManager;

    private OnFloatViewClickListener clickListener;

    public static void creatFloatWindow(Context context, OnFloatViewClickListener listener) {
        windowManager = getWindowManager(context);
        if (floatView == null) {
            floatView = new FloatView(context);
            if (floatParams == null) {
                floatParams = new LayoutParams();
                floatParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                floatParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
                floatParams.x = 15;
                floatParams.y = 170;
                floatParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | LayoutParams.FLAG_NOT_FOCUSABLE;
//              不加这一句花，会出现悬浮按钮有黑边框
                floatParams.format = PixelFormat.RGBA_8888;
                floatParams.width = LayoutParams.WRAP_CONTENT;
                floatParams.height = LayoutParams.WRAP_CONTENT;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    floatParams.type = LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    floatParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
                }
            }
            floatView.setLayoutParams(floatParams);
            floatView.setClickListener(listener);
            windowManager.addView(floatView, floatParams);
        }
    }

    /**
     * 移除悬浮窗
     *
     * @param context
     */
    public static void removeFloatView(Context context) {
        if (floatView != null) {
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
     *
     * @return
     */
    public static boolean hasFloatWindow() {
        return floatView == null ? false : true;
    }

}
