package com.example.huangjie.cloudmusic.view;

import android.content.Intent;
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
import android.widget.ImageView;

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

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initView();

            initEvent();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_play_music);
        mCurrentMusic = (MusicBean) getIntent().getSerializableExtra(Constant.CURRENT_MUSIC);
        Log.i("huangjie",mCurrentMusic.toString()+"这是 playmusic");
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
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mMusicBeanList = new ArrayList<>();
        mMusicBeanList = MusicUtils.getAllMusic(null,null,mHandler);
    }


    /**
     * 初始化事件
     */
    private void initEvent() {

        mImgStart.setOnClickListener(this);
        mImgNext.setOnClickListener(this);
        mImgPrecious.setOnClickListener(this);
        mImgPlayRule.setOnClickListener(this);
        mImgMuicList.setOnClickListener(this);

        setViewPagerAdater();//给ViewPager设置adapter
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mImgNeddle.clearAnimation();
            }

            @Override
            public void onPageSelected(int position) {
                startAnimation();
                SharePreferenceUtils.putPlayStatus(true);
                mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_pause));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        if (SharePreferenceUtils.getPlayStatus()) {
            mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_pause));
            startAnimation();
        } else {
            mImgStart.setImageDrawable(Utils.getDrawable(R.drawable.play_btn_play));
            mImgNeddle.clearAnimation();
        }

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

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);
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
                }
                break;
            case R.id.id_playmusic_next:
                break;
            case R.id.id_playmusic_precious:
                break;
            case R.id.id_playmusic_playrule:
                break;
            case R.id.id_playmusic_musiclist:
                break;
        }
    }

    /**
     * 停止动画
     */
    private void stopAnimation() {
        mImgNeddle.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_d_45);
        animation.setFillAfter(true);
        mImgNeddle.startAnimation(animation);
        sendBroadcast(new Intent(Constant.ACTION_STOP_PLAY));
    }

    /**
     * 开始动画效果
     */
    private void startAnimation() {
        mImgNeddle.clearAnimation();
        int currentItem = mViewPager.getCurrentItem();
        mCurrentMusic = mMusicBeanList.get(currentItem);//获取当前的音乐
        mImageViewList.get(currentItem).startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_45);
        animation.setFillAfter(true);
        mImgNeddle.startAnimation(animation);
        Intent intent = new Intent(this, PlayMusicService.class);
        intent.putExtra(Constant.CURRENT_MUSIC, mCurrentMusic.getUrl());
        startService(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharePreferenceUtils.putPlayStatus(false);
    }
}
