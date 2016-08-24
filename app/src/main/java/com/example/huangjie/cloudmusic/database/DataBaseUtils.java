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
     * @param id
     * @param name
     * @param special
     * @param songerName
     * @param url
     * @param duration
     * @param size
     */
    public static void insert(int id, String name, String special, String songerName,
                              String url, float duration, String size) {
        SQLiteDatabase database = MusicDatabaseHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constant.MUSIC_DB_ID, id);
        values.put(Constant.MUSIC_DB_MUSICNAME, name);
        values.put(Constant.MUSIC_DB_SPECIAL, special);
        values.put(Constant.MUSIC_DB_SONGERNAME, songerName);
        values.put(Constant.MUSIC_DB_URL, url);
        values.put(Constant.MUSIC_DB_DURATION, duration);
        values.put(Constant.MUSIC_DB_SIZE, size);

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
}
