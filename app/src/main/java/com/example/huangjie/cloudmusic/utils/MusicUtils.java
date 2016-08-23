package com.example.huangjie.cloudmusic.utils;

import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.bean.MusicBean;
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
    public static ArrayList<MusicBean> getAllMusic(final View view, final TextView textView) {
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
                    Log.i("huangjie",musicBean.toString());
                    mMusicBeanList.add(musicBean);
                    int album_id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
                if (textView!=null){
                    textView.setText(mMusicBeanList.size()+"");
                }
            }
        }.execute();

        return mMusicBeanList;

    }
}
