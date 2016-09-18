package com.example.huangjie.cloudmusic.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.example.huangjie.cloudmusic.utils.SharePreferenceUtils;
import com.example.huangjie.cloudmusic.utils.ViewHolder;
import com.example.huangjie.cloudmusic.view.MoreBottomSheetDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangjie on 2016/8/22.
 */
public class LocalMusicActivity extends AppCompatActivity implements View.OnClickListener, MoreBottomSheetDialog.IPlayNext, MoreBottomSheetDialog.IShareSong, MoreBottomSheetDialog.IdeleteSong {
    @BindView(R.id.id_localmusic_musicList)
    ListView mMusicListView;
    @BindView(R.id.id_localmusic_playall)
    LinearLayout mLinearPlayAll;
    ArrayList<MusicBean> mMusicBeanData;
    @BindView(R.id.id_toolbar_title_back)
    ImageView mImgBack;
    @BindView(R.id.id_toolbar_title_search)
    ImageView mImgSearch;
    @BindView(R.id.id_toolbar_title_title)
    TextView mTitle;
    MoreBottomSheetDialog mBottomSheetDialog;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setListViewAdater();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_localmusic_activity);
        ButterKnife.bind(this);
        mTitle.setText("本地音乐");
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
                intent.putExtra(Constant.CURRENT_MUSIC, mMusicBeanData.get(position));
                intent.putExtra(Constant.CURRENT_POSITION, position);
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
     * 设置ListViewAdapter
     */
    private void setListViewAdater() {
        SharePreferenceUtils.putMusicNumber(mMusicBeanData.size());//存储系统中的所有的音乐文件数量
        mMusicListView.setAdapter(new CommonAdapter<MusicBean>(LocalMusicActivity.this, mMusicBeanData, R.layout.localmusic_list_item) {
            @Override
            public void convert(ViewHolder viewHolder, final MusicBean data, final int position) {
                viewHolder.setTextView(R.id.id_musiclist_item_songname, data.getName());
                if (!data.getSongerName().equals("<unknown>")) {
                    viewHolder.setTextView(R.id.id_musiclist_item_songner, data.getSongerName());
                }

                //Log.i("huangjie", "这是数据 决定是否是多少vsvs从vwefsdcsddsvv" + data.toString());
                viewHolder.setTextView(R.id.id_musiclist_item_songspecial, data.getSpecial());
                viewHolder.getViewById(R.id.id_musiclist_item_more).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetDialog = new MoreBottomSheetDialog(LocalMusicActivity.this);
                        mBottomSheetDialog.setRootView(R.layout.more_popwindow);
                        mBottomSheetDialog.setData(data);
                        mBottomSheetDialog.show();
                        mBottomSheetDialog.setPlayNext(LocalMusicActivity.this);
                        mBottomSheetDialog.setDelete(LocalMusicActivity.this);
                        mBottomSheetDialog.setShare(LocalMusicActivity.this);
                        mBottomSheetDialog = null;

                    }
                });
            }
        });
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
        }
    }


    @Override
    public void next() {
        MusicUtils.playNext();
        Log.i("huangjie", "执行了++++jiekou");
    }

    @Override
    public void share(MusicBean bean) {

    }

    @Override
    public void delete(MusicBean bean) {

    }
}


