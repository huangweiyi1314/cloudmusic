package com.example.huangjie.cloudmusic.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.constant.Constant;
import com.example.huangjie.cloudmusic.global.CloudMusicApplication;
import com.example.huangjie.cloudmusic.service.PlayMusicService;
import com.example.huangjie.cloudmusic.utils.MusicUtils;
import com.example.huangjie.cloudmusic.utils.SharePreferenceUtils;
import com.example.huangjie.cloudmusic.utils.Utils;

import java.util.ArrayList;

/**
 * Created by huangjie on 2016/8/23.
 */
public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private ArrayList<MusicBean> mMusicBeanList;
    private ImageView mImgStart;
    private ImageView mImgNext;
    private ImageView mImgPrecious;
    private ImageView mImgMuicList;
    private ImageView mImgPlayRule;
    private MusicBean mCurrentMusic;
    private ArrayList<ImageView> mImageViewList;
    private ImageView mImgNeddle;
    private int mCurrentPosition;
    private ImageView mImgBack;
    private TextView tvSongName;
    private TextView tvSongner;
    private SeekBar mPlayProgress;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initView();
            mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_pause));
            initEvent();

        }
    };

    private BroadcastReceiver mReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                playNext();
            Log.i("huangjie","播放下一首");
        }
    };

    /**
     * 播放下一首
     */
    private void playNext() {
        mCurrentPosition++;
        mViewPager.setCurrentItem(mCurrentPosition);
        startAnimation();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_play_music);
        //获取传递过来的musicBean
        mCurrentMusic = (MusicBean) getIntent().getSerializableExtra(Constant.CURRENT_MUSIC);
        mCurrentPosition = getIntent().getIntExtra(Constant.CURRENT_POSITION, 1);
        Log.i("huangjie", "这是第一个执行");

        if (mCurrentMusic == null) {
            mCurrentMusic = mMusicBeanList.get(0);
        }
        initData();

        //判断当前的系统版本
        if (Build.VERSION.SDK_INT >= 21) {
            View view = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            view.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }

        //注册广播接收器
        registerReceiver(mReceiver,new IntentFilter(Constant.PLAY_NEXT));
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mMusicBeanList = new ArrayList<>();
        mMusicBeanList = MusicUtils.getAllMusic(null, null, mHandler);
    }


    /**
     * 初始化事件
     */
    private void initEvent() {
        /**
         * 给ViewPager设置adapter
         */
        setViewPagerAdater();

        mImgStart.setOnClickListener(this);
        mImgNext.setOnClickListener(this);
        mImgPrecious.setOnClickListener(this);
        mImgPlayRule.setOnClickListener(this);
        mImgMuicList.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_pause));
                SharePreferenceUtils.putPlayStatus(true);
                mCurrentPosition = position;//更新当前的position
                startAnimation();
                mCurrentMusic = mMusicBeanList.get(position);
                resetTitle();
                //    Log.i("huangjie", mMusicBeanList.get(position).toString() + "这是切换数据");
                startService(position);
                Log.i("huangjie", "切换当前执行222222222222");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("huangjie", "切换当前执行333333333" + state);
                switch (state) {
                    case 1:
                        mImgNeddle.clearAnimation();
                        if (SharePreferenceUtils.getPlayStatus()) {
                            //  mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_pause));
                            stopAnimation();
                            SharePreferenceUtils.putPlayStatus(false);
                        }
                        break;
                }
            }
        });

        mPlayProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("huangjie", progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Log.i("huangjie", "这是第二个执行");
        mViewPager.setCurrentItem(mCurrentPosition);
       // startService(mCurrentPosition);
        startAnimation();//开始动画
    }


    /**
     * 初始化View
     */
    private void initView() {
        //初始化ViewPager中的view
        mImageViewList = new ArrayList<>();
        for (int i = 0; i < SharePreferenceUtils.getMusicNumber(); i++) {
            ImageView imageView = new ImageView(CloudMusicApplication.getContext());
            imageView.setImageDrawable(Utils.getDrawable(R.drawable.placeholder_disk_play_program));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            mImageViewList.add(imageView);
        }
        mViewPager = (ViewPager) findViewById(R.id.id_playmusic_viewpager);
        mImgStart = (ImageView) findViewById(R.id.id_playmusic_startplay);
        mImgNext = (ImageView) findViewById(R.id.id_playmusic_next);
        mImgPrecious = (ImageView) findViewById(R.id.id_playmusic_precious);
        mImgPlayRule = (ImageView) findViewById(R.id.id_playmusic_playrule);
        mImgMuicList = (ImageView) findViewById(R.id.id_playmusic_musiclist);
        mImgNeddle = (ImageView) findViewById(R.id.id_playmusic_neddle);
        mImgBack = (ImageView) findViewById(R.id.id_playmusic_back);
        tvSongName = (TextView) findViewById(R.id.id_playmusic_songname);
        tvSongner = (TextView) findViewById(R.id.id_playmusic_songner);
        mPlayProgress = (SeekBar) findViewById(R.id.id_playmusic_seekbar);
        resetTitle();//设置当前title

    }

    /**
     * 给ViewPager设置Adater
     */
    private void setViewPagerAdater() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return SharePreferenceUtils.getMusicNumber();
            }

            @Override
            public ImageView instantiateItem(ViewGroup container, int position) {
                ImageView imageView = mImageViewList.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_playmusic_startplay:
                if (SharePreferenceUtils.getPlayStatus()) {
                    mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_play));
                    SharePreferenceUtils.putPlayStatus(false);
                    stopAnimation();
                } else {
                    mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_pause));
                    SharePreferenceUtils.putPlayStatus(true);
                    startAnimation();
                    startService(mCurrentPosition);
                }
                break;
            case R.id.id_playmusic_next:
                mCurrentPosition++;
                mViewPager.setCurrentItem(mCurrentPosition);

                break;
            case R.id.id_playmusic_precious:
                mCurrentPosition--;
                mViewPager.setCurrentItem(mCurrentPosition);

                break;
            case R.id.id_playmusic_playrule:
                break;
            case R.id.id_playmusic_musiclist:
                break;
            case R.id.id_playmusic_back:
                finish();
                break;
        }
    }

    /**
     * 停止动画
     */
    private void stopAnimation() {
        mImgNeddle.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_d_45);
        mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_play));
        animation.setFillAfter(true);
        mImgNeddle.startAnimation(animation);
        sendBroadcast(new Intent(Constant.ACTION_STOP_PLAY));
    }

    /**
     * 开始动画效果
     */
    private void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(PlayMusicActivity.this, R.anim.rotate_45);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFillAfter(true);
        mImgNeddle.startAnimation(animation);

    }

    /**
     * 打开Service
     */
    public void startService(int position) {

        mImageViewList.get(position).startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));

        Intent intent = new Intent(this, PlayMusicService.class);
        /*if (SharePreferenceUtils.getPlayStatus()){
            intent.setAction(Constant.ACTION_PAUSE);
        }else{

        }*/
        intent.putExtra(Constant.CURRENT_MUSIC, mMusicBeanList.get(position).getUrl());
        startService(intent);
    }

    /**
     * 重置当前的显示的歌曲名称
     */
    public void resetTitle() {
        tvSongName.setText(mCurrentMusic.getName());

        tvSongner.setText(mCurrentMusic.getSongerName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
