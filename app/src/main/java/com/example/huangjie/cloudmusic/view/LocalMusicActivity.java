package com.example.huangjie.cloudmusic.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.constant.Constant;
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
    private MorePopWindow mMorePopWindow;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mMusicListView.setAdapter(new CommonAdapter<MusicBean>(LocalMusicActivity.this, mMusicBeanData, R.layout.localmusic_list_item) {
                @Override
                public void convert(ViewHolder viewHolder, MusicBean data, final int position) {
                    viewHolder.setTextView(R.id.id_musiclist_item_songname, data.getName());
                    if (!data.getSongerName().equals("<unknown>")) {
                        viewHolder.setTextView(R.id.id_musiclist_item_songner, data.getSongerName());
                    }
                    mMorePopWindow.setData(data);
                    //Log.i("huangjie", "这是数据 决定是否是多少vsvs从vwefsdcsddsvv" + data.toString());
                    viewHolder.setTextView(R.id.id_musiclist_item_songspecial, data.getSpecial());
                    mMorePopWindow.setData(mMusicBeanData.get(position));

                    viewHolder.getViewById(R.id.id_musiclist_item_more).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mMorePopWindow != null) {
                                mMorePopWindow.setData(mMusicBeanData.get(position));
                                mMorePopWindow.showAtLocation(findViewById(R.id.id_localmusic_playall), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                setPopWindow();
                            }
                        }
                    });
                }
            });
        }
    };

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
        mMusicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LocalMusicActivity.this, PlayMusicActivity.class);
                intent.putExtra(Constant.SONG_DETAIL, mMusicBeanData.get(position));
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initDate() {
        final TextView songNumber = (TextView) findViewById(R.id.id_localmusic_songnumber);
        mMusicBeanData = MusicUtils.getAllMusic(null, songNumber, mHandler);

    }

    /**
     * 初始化
     */
    private void initView() {
        mLinearPlayAll = (LinearLayout) findViewById(R.id.id_localmusic_playall);
        mMusicListView = (ListView) findViewById(R.id.id_localmusic_musicList);
        mMusicListView.setDivider(new ColorDrawable(getResources().getColor(R.color.lightgray)));
        mMusicListView.setDividerHeight(1);
        mImgBack = (ImageView) findViewById(R.id.id_toolbar_title_back);
        mImgSearch = (ImageView) findViewById(R.id.id_toolbar_title_search);
        mTitle = (TextView) findViewById(R.id.id_toolbar_title_title);
        mTitle.setText("本地音乐");
        mMorePopWindow = new MorePopWindow(this);
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

    /**
     * 设置PopWindow中的回掉接口
     */
    private void setPopWindow() {
        mMorePopWindow.setPlayNext(new MorePopWindow.IPlayNext() {
            @Override
            public void nect() {

            }
        });
        mMorePopWindow.setDelet(new MorePopWindow.IdeleteSong() {
            @Override
            public void delete(MusicBean bean) {

            }
        });
        mMorePopWindow.setShare(new MorePopWindow.IShareSong() {
            @Override
            public void share(MusicBean bean) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("audio/mpeg");
            }
        });
    }
}


