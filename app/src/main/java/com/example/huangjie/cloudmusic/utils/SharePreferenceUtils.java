package com.example.huangjie.cloudmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.huangjie.cloudmusic.constant.Constant;

/**
 * Created by huangjie on 2016/8/24.
 */
public class SharePreferenceUtils {
    private static SharedPreferences mSharePreference;

    public static SharedPreferences getInstance() {
        if (mSharePreference == null) {
            mSharePreference = Utils.getContext().getSharedPreferences("CLOUD_MUSIC", Context.MODE_PRIVATE);

        }
        return mSharePreference;
    }

    /**
     * 存储当前播放状态
     *
     * @param isplay
     */
    public static void putPlayStatus(boolean isplay) {
        SharedPreferences.Editor editor =getInstance().edit();
        editor.putBoolean(Constant.IS_PLAY, isplay);
        editor.commit();
    }

    /**
     * 获取当前播放状态
     *
     * @return
     */
    public static boolean getPlayStatus() {
        return getInstance().getBoolean(Constant.IS_PLAY, false);
    }

    /**
     * 存储系统中所有的音乐数量
     * @param number
     */
    public static  void putMusicNumber(int number){
        SharedPreferences.Editor edit = getInstance().edit();
        edit.putInt(Constant.ALL_MUSIC_NUMBER,number);
        edit.commit();
    }

    /**
     * 获取系统中所有的音乐文件数量
     * @return
     */
    public  static int getMusicNumber(){
        return getInstance().getInt(Constant.ALL_MUSIC_NUMBER,73);

    }


}
