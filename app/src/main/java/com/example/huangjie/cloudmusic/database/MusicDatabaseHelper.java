package com.example.huangjie.cloudmusic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.huangjie.cloudmusic.constant.Constant;
import com.example.huangjie.cloudmusic.utils.Utils;

/**
 * Created by huangjie on 2016/8/24.
 */
public class MusicDatabaseHelper extends SQLiteOpenHelper {

    public static MusicDatabaseHelper instance;

    private MusicDatabaseHelper(Context context) {
        super(context, Constant.MUSIC_DB_NAME, null, 1);
    }

    /**
     * 获取dbopenhelper 单例对象
     *
     * @return
     */
    public static MusicDatabaseHelper getInstance() {
        if (instance == null) {
            synchronized (MusicDatabaseHelper.class) {
                if (instance == null) {
                    instance = new MusicDatabaseHelper(Utils.getContext());
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constant.CREATE_MUSIC_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
