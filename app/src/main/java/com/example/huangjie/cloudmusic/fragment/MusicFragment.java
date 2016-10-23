package com.example.huangjie.cloudmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.activity.RecentPlayActivity;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.database.DataBaseUtils;
import com.example.huangjie.cloudmusic.utils.MusicUtils;
import com.example.huangjie.cloudmusic.activity.LocalMusicActivity;
import com.example.huangjie.cloudmusic.utils.SharePreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangjie on 2016/8/18.
 */
public class MusicFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.id_fragment_music_progreressbar)
    ProgressBar mProgressBar;
    @BindView(R.id.id_fragment_music_localmusNum)
    TextView mLocalMusicNum;
    @BindView(R.id.id_fragment_music_recentmusicNum)
    TextView mRecentMusicNum;
    @BindView(R.id.id_fragment_music_downmusNum)
    TextView mDownMusicNum;
    @BindView(R.id.id_fragment_music_mysongernum)
    TextView mSongerMusicNum;
    @BindView(R.id.id_fragment_music_localMusic)
    LinearLayout mLinearLocal;
    @BindView(R.id.id_fragment_music_recentMusic)
    LinearLayout mLinearRecent;
    @BindView(R.id.id_fragment_music_downMusic)
    LinearLayout mLinearDown;
    @BindView(R.id.id_fragment_music_mysonger)
    LinearLayout mLinearSonger;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_music, container, false);
        ButterKnife.bind(this, view);
        mLocalMusicNum.setText(SharePreferenceUtils.getMusicNumber()+"");
        initEvent();
        return view;
    }


    /**
     * 初始化事件
     */
    private void initEvent() {
        mLinearLocal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_fragment_music_localMusic:
                intent.setClass(getActivity(), LocalMusicActivity.class);
                break;
            case R.id.id_fragment_music_recentMusic:
                intent.setClass(getActivity(), RecentPlayActivity.class);
                break;
            case R.id.id_fragment_music_downMusic:
                break;
            case R.id.id_fragment_music_mysonger:
                break;
        }
        startActivity(intent);
    }

}
