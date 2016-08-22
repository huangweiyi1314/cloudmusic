package com.example.huangjie.cloudmusic.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.huangjie.cloudmusic.global.CloudMusicApplication;

/**
 * Created by huangjie on 2016/8/7.
 */
public class Utils {

    /**
     * 获取context
     *
     * @return
     */
    public static Context getContext() {
        return CloudMusicApplication.getContext();
    }

    /**
     * 获取color
     *
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    /**
     * 获取drawable
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    /**
     * 加载view
     *
     * @param layoutid
     * @return
     */
    public static View inflate(int layoutid) {
        View view = LayoutInflater.from(getContext()).inflate(layoutid, null);
        return view;
    }

    /**
     * 获取drawable
     *
     * @param id
     * @return
     */
    public static Drawable getDrawables(int id) {
        Drawable drawable = getContext().getResources().getDrawable(id);
        return drawable;
    }


    /**
     * Logutils
     * @param text
     */
    public  static  void LogUtil(String text){
        Log.i("huangjie",text);
    }

}
