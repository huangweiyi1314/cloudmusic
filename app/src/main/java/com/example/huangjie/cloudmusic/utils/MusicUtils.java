package com.example.huangjie.cloudmusic.utils;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.constant.Constant;
import com.example.huangjie.cloudmusic.database.DataBaseUtils;
import com.example.huangjie.cloudmusic.global.CloudMusicApplication;

import java.util.ArrayList;

/**
 * Created by huangjie on 2016/8/23.
 */
public class MusicUtils {
    /**
     * 获取所有的手机上的music
     *
     * @return
     */
    public static void getAllMusic(final View view, final CallBack callBack) {
        final ArrayList<MusicBean> mMusicBeanList = new ArrayList<>();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                Cursor cursor = CloudMusicApplication.getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
                while (cursor.moveToNext()) {
                    MusicBean musicBean = new MusicBean();
                    int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    musicBean.setId(id);

                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    musicBean.setName(name);
                    // cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    musicBean.setUrl(url);

                    String speical = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    musicBean.setSpecial(speical);

                    String songerName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    musicBean.setSongerName(songerName);

                    float duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                    musicBean.setduration(duration);

                    int size = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                    musicBean.setSize((int) ((size * 1.0) / (1024 * 1024)) + "M");

                    mMusicBeanList.add(musicBean);

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (callBack != null) {
                    SharePreferenceUtils.putMusicNumber(mMusicBeanList.size());
                    callBack.success(mMusicBeanList);
                }
            }
        }.execute();

    }

    /**
     * 播放下一首
     *
     */
    public static void playNext() {
        Utils.getContext().sendBroadcast(new Intent(Constant.PLAY_NEXT));
    }

    public interface CallBack {
        void success(ArrayList<MusicBean> datalist);
    }
}
