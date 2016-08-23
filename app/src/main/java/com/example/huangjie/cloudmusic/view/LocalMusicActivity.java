package com.example.huangjie.cloudmusic.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.utils.CommonAdapter;
import com.example.huangjie.cloudmusic.utils.MusicUtils;
import com.example.huangjie.cloudmusic.utils.ViewHolder;

import java.util.ArrayList;

/**
 * Created by huangjie on 2016/8/22.
 */
public class LocalMusicActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mMusicListView;
    private LinearLayout mLinearPlayAll;
    private ArrayList<MusicBean> mMusicBeanData;
    private ImageView mImgBack;
    private ImageView mImgSearch;
    private TextView mTitle;
    private MusicBean mCurrentMusicBean;
    private MorePopWindow mMorePopWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_localmusic_activity);
        initView();
        initDate();
        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        mImgBack.setOnClickListener(this);
        mImgSearch.setOnClickListener(this);
        mLinearPlayAll.setOnClickListener(this);

        mMusicListView.setAdapter( new CommonAdapter<MusicBean>(this, mMusicBeanData, R.layout.localmusic_list_item) {
            @Override
            public void convert(ViewHolder viewHolder, MusicBean data) {
                viewHolder.setTextView(R.id.id_musiclist_item_songname, data.getName());
                if (!data.getSongerName().equals("<unknown>")) {
                    viewHolder.setTextView(R.id.id_musiclist_item_songner, data.getSongerName());
                }
                viewHolder.setTextView(R.id.id_musiclist_item_songspecial, data.getSpecial());
                viewHolder.getViewById(R.id.id_musiclist_item_more).setOnClickListener(LocalMusicActivity.this);
                mCurrentMusicBean = data;
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initDate() {
        TextView songNumber = (TextView) findViewById(R.id.id_localmusic_songnumber);
        mMusicBeanData = MusicUtils.getAllMusic(null, songNumber);
    }

    /**
     * 初始化
     */
    private void initView() {
        mLinearPlayAll = (LinearLayout) findViewById(R.id.id_localmusic_playall);
        mMusicListView = (ListView) findViewById(R.id.id_localmusic_musicList);
        mImgBack = (ImageView) findViewById(R.id.id_toolbar_title_back);
        mImgSearch = (ImageView) findViewById(R.id.id_toolbar_title_search);
        mTitle = (TextView) findViewById(R.id.id_toolbar_title_title);
        mTitle.setText("本地音乐");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_toolbar_title_back:
                finish();
                break;
            case R.id.id_toolbar_title_search:

                break;
            case R.id.id_localmusic_playall:

                break;
            case R.id.id_musiclist_item_more:

                break;

        }
    }
}
