package com.example.huangjie.cloudmusic.global;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by huangjie on 2016/8/17.
 */
public class CloudMusicApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private int mMainThreadID;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadID = android.os.Process.myTid();

    }

    /**
     * 获取application Context
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取handler
     *
     * @return
     */
    public Handler getmHandler() {

        return mHandler;
    }

    /**
     * 获取主线程id
     *
     * @return
     */
    public int getMainThreadID() {
        return mMainThreadID;
    }


    /**
     * 获取当前进程name
     *
     * @param context
     * @return
     */
    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : activityManager.getRunningAppProcesses()) {
            if (info.pid == pid) {
                return info.processName;
            }

        }
        return null;

    }
}
