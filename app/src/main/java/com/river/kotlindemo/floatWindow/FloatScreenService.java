package com.river.kotlindemo.floatWindow;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;

import com.river.kotlindemo.activitys.SecondActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FloatScreenService extends Service {
    public FloatScreenService() {
    }

    private Handler mHandler = new Handler();
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new ResreshWindow(), 0, 500);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
//      关闭服务时，销毁悬浮框
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                FloatWindowManager.removeFloatView(getApplicationContext());
            }
        });
    }

    class ResreshWindow extends TimerTask {

        @Override
        public void run() {
            /**
             * 根据不同页面来判断是否显示悬浮按钮
             *
             * 桌面，投屏页 不显示
             *
             */
            if (!isShowWindow() && FloatWindowManager.hasFloatWindow()) {//不应该展示悬浮按钮，但是有了悬浮按钮  需要隐藏按钮
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        FloatWindowManager.removeFloatView(getApplicationContext());
                    }
                });
            } else if (isShowWindow() && !FloatWindowManager.hasFloatWindow()) {//应该展示悬浮窗，但无悬浮窗。需要增加
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        FloatWindowManager.creatFloatWindow(getApplicationContext(), new OnFloatViewClickListener() {
                            @Override
                            public void onClick() {
                                Intent it = new Intent(FloatScreenService.this, SecondActivity.class);
                                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(it);
                            }
                        });
                    }
                });
            }
        }
    }

    /**
     * 判断当前界面是否是桌面
     */
    private boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        return getHomes().contains(rti.get(0).topActivity.getPackageName());
    }

    /**
     * 判断是否要显示悬浮按钮
     *
     * @return true 要显示  false  隐藏
     */
    private boolean isShowWindow() {
        if (isHome()) {
            return false;
        } else {
            ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
            if (rti.get(0).topActivity.getPackageName().contains("com.river.kotlindemo")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private List<String> getHomes() {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }

}
