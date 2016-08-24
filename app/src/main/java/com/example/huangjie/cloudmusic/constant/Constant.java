package com.example.huangjie.cloudmusic.constant;

/**
 * Created by huangjie on 2016/8/19.
 */
public class Constant {
    public static  final int FRAGMENT_SUGGEST_VIEWPAGERCOUNT=4;//首页轮播条数目
    public static  final String SONG_DETAIL="song_detail";
    public static  final String IS_PLAY="is_play";
    public static  final String CURRENT_MUSIC="current_music";//目前正在传递的musicBean
    public static  final String ALL_MUSIC_NUMBER="all_music_number";//手机中所有的音乐数量
    public static  final String ACTION_STOP_PLAY="action_stop_play";//停止播放action
    public static  final String ACTION_GO_ON_PLAY="action_go_on_play";//继续播放action
    public static  final String ALL_MUSIC_LIST="all_music_list";
    public static  final String MUSIC_DB_NAME="cloudmusic";
    public static  final String CREATE_MUSIC_DB="create table music(" +
                                 "id integer primary key autoincrement," +
                                  "music_id integer,"+
                                 "name text," +
                                 " special text," +
                                 "songerName text," +
                                  "url text," +
                                 "duration float," +
                                  "size text )";

    public static  final String MUSIC_DB_ID="music_id";
    public static  final String MUSIC_DB_MUSICNAME="name";
    public static  final String MUSIC_DB_SPECIAL="special";
    public static  final String MUSIC_DB_SONGERNAME="songerName";
    public static  final String MUSIC_DB_URL="url";
    public static  final String MUSIC_DB_DURATION="duration";
    public static  final String MUSIC_DB_SIZE="size";

    public static  final String MUSIC_TABLE_NAME="music";

}
