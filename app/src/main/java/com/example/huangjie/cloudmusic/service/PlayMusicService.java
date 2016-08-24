package com.example.huangjie.cloudmusic.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.constant.Constant;
import com.example.huangjie.cloudmusic.utils.SharePreferenceUtils;

import java.io.IOException;

/**
 * Created by huangjie on 2016/8/24.
 */
public class PlayMusicService extends Service {
    private MediaPlayer mMediaPlayer;
    private MusicBean mMusicBean;
    private String mcurrentUrl;
    //广播接收器
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constant.ACTION_STOP_PLAY)) {
                pausePlay();
            } else if (intent.getAction().equals(Constant.ACTION_GO_ON_PLAY)) {
                startPlay(mMusicBean.getUrl());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        //注册广播接受
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_STOP_PLAY);
        intentFilter.addAction(Constant.ACTION_GO_ON_PLAY);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mcurrentUrl = (String) intent.getSerializableExtra(Constant.CURRENT_MUSIC);
      //  Log.i("huangjie",mcurrentUrl+"URL+++++++++");

        if (mcurrentUrl!=null){
            startPlay(mcurrentUrl);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void startPlay(final String url) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                if (mMediaPlayer!=null) {
                    mMediaPlayer.release();
                    mMediaPlayer = null;
                }
                mMediaPlayer = new MediaPlayer();
                SharePreferenceUtils.putPlayStatus(true);
                try {
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mMediaPlayer.setDataSource(url);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();

                } catch (IOException e) {
                    SharePreferenceUtils.putPlayStatus(false);
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 暂停播放
     */
    public void pausePlay() {
       mMediaPlayer.release();
            SharePreferenceUtils.putPlayStatus(false);

    }


    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        SharePreferenceUtils.putPlayStatus(false);

    }
}
