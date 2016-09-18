package com.example.huangjie.cloudmusic.database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.constant.Constant;

/**
 * Created by huangjie on 2016/8/24.
 */
public class DataBaseUtils {

    /**
     * 存储音乐数据到数据库
     *
     *
     */
    public static void insert(MusicBean musicBean) {
        SQLiteDatabase database = MusicDatabaseHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.MUSIC_DB_ID, musicBean.getId());
        values.put(Constant.MUSIC_DB_MUSICNAME, musicBean.getName());
        values.put(Constant.MUSIC_DB_SPECIAL, musicBean.getSpecial());
        values.put(Constant.MUSIC_DB_SONGERNAME, musicBean.getSongerName());
        values.put(Constant.MUSIC_DB_URL, musicBean.getUrl());
        values.put(Constant.MUSIC_DB_DURATION, musicBean.getduration());
        values.put(Constant.MUSIC_DB_SIZE, musicBean.getSize());

        database.insert(Constant.MUSIC_TABLE_NAME, null, values);
        database.close();
    }

    /**
     * 根据ID查到MusicBean对象
     *
     * @param id
     * @return
     */
    public static MusicBean search(int id) {
        MusicBean musicBean = new MusicBean();
        SQLiteDatabase database = MusicDatabaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = database.query(Constant.MUSIC_TABLE_NAME, null, "id =" + id, new String[]{id + ""}, null, null, null);
        while (cursor.moveToNext()) {
            int searchID = cursor.getInt(cursor.getColumnIndex(Constant.MUSIC_DB_ID));
            if (searchID == id) {
                musicBean.setId(searchID);
                musicBean.setName(cursor.getString(cursor.getColumnIndex(Constant.MUSIC_DB_MUSICNAME)));
                musicBean.setSpecial(cursor.getString(cursor.getColumnIndex(Constant.MUSIC_DB_SPECIAL)));
                musicBean.setUrl(cursor.getString(cursor.getColumnIndex(Constant.MUSIC_DB_URL)));
                musicBean.setduration(cursor.getFloat(cursor.getColumnIndex(Constant.MUSIC_DB_DURATION)));
                musicBean.setSize(cursor.getString(cursor.getColumnIndex(Constant.MUSIC_DB_SIZE)));
                return musicBean;
            }
        }
        database.close();
        cursor.close();
        return null;

    }

    /**
     * 获取数据库大小
     * @return
     */
    public static  long getDataBaseSize(){
        SQLiteDatabase database = MusicDatabaseHelper.getInstance().getReadableDatabase();
     return  database.getMaximumSize();

    }
}
