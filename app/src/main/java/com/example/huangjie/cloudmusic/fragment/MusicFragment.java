package com.example.huangjie.cloudmusic.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.utils.MusicUtils;
import com.example.huangjie.cloudmusic.view.LocalMusicActivity;

import java.util.ArrayList;

/**
 * Created by huangjie on 2016/8/18.
 */
public class MusicFragment extends Fragment implements View.OnClickListener {

    private ProgressBar mProgressBar;
    private ArrayList<MusicBean> mMusicBeanList;
    private TextView mLocalMusicNum;
    private TextView mRecentMusicNum;
    private TextView mDownMusicNum;
    private TextView mSongerMusicNum;
    private LinearLayout mLinearLocal;
    private LinearLayout mLinearRecent;
    private LinearLayout mLinearDown;
    private LinearLayout mLinearSonger;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_music, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }


    /**
     * 初始化事件
     */
    private void initEvent() {
        mLinearLocal.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mMusicBeanList = MusicUtils.getAllMusic(mProgressBar,mLocalMusicNum,null);
    }

    /**
     * 初始化View
     */
    private void initView(View view) {
        mProgressBar = (ProgressBar) view.findViewById(R.id.id_fragment_music_progreressbar);
        mLinearLocal = (LinearLayout) view.findViewById(R.id.id_fragment_music_localMusic);
        mLinearRecent = (LinearLayout) view.findViewById(R.id.id_fragment_music_recentMusic);
        mLinearDown = (LinearLayout) view.findViewById(R.id.id_fragment_music_downMusic);
        mLinearSonger = (LinearLayout) view.findViewById(R.id.id_fragment_music_mysonger);
        mLocalMusicNum = (TextView) view.findViewById(R.id.id_fragment_music_localmusNum);
        mRecentMusicNum = (TextView) view.findViewById(R.id.id_fragment_music_recentmusicNum);
        mDownMusicNum = (TextView) view.findViewById(R.id.id_fragment_music_downmusNum);
        mSongerMusicNum = (TextView) view.findViewById(R.id.id_fragment_music_mysongernum);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_fragment_music_localMusic:
                intent.setClass(getActivity(), LocalMusicActivity.class);
                break;
            case R.id.id_fragment_music_recentMusic:
                break;
            case R.id.id_fragment_music_downMusic:
                break;
            case R.id.id_fragment_music_mysonger:
                break;
        }
        startActivity(intent);
    }

}
