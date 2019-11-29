package com.river.kotlindemo.floatWindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;


import androidx.annotation.Nullable;

import com.river.kotlindemo.R;

import java.lang.reflect.Field;

/**
 * 悬浮控件
 * Created by zhangyanpeng on 2019/11/14
 */
public class FloatView extends LinearLayout {

    /**
     * 悬浮框宽度
     */
    private static int fWidth;

    /**
     * 悬浮框高度
     */
    private static int fHeight;

    /**
     * 系统状态栏高度
     */
    private static int statusBarHeight;

    /**
     * 窗口管理
     */
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    /**
     * 当前手指在屏幕上的横纵坐标
     */
    private float xInScreen;
    private float yInScreen;

    /**
     * 手指按下时 在屏幕上的横纵坐标
     */
    private float xDownScreen;
    private float yDownScreen;

    /**
     * 手指按下时，悬浮窗在屏幕上的坐标
     */
    private float xViewScreen;
    private float yViewScreen;

    /**
     * 点击事件
     * @param context
     */
    private OnFloatViewClickListener clickListener;

    public void setClickListener(OnFloatViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public FloatView(Context context) {
        this(context, null);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.window_float_layout, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xViewScreen = event.getX();
                yViewScreen = event.getY();

                xDownScreen = event.getRawX();
                yDownScreen = event.getRawY() - getStatusBarHeight();

                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();

                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
//                updateWindow();
                break;
            case MotionEvent.ACTION_UP:
                if (xDownScreen == xInScreen&&yDownScreen == yInScreen){
//                  点击事件
                    onClick();
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.layoutParams = layoutParams;
    }

    private void updateWindow() {
        layoutParams.x = (int) (xInScreen-xViewScreen);
        layoutParams.y = (int) (yInScreen-yViewScreen);
        windowManager.updateViewLayout(this,layoutParams);
    }

    /**
     * 点击事件
     */
    public void onClick(){
        if (clickListener!=null){
            clickListener.onClick();
        }
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}
