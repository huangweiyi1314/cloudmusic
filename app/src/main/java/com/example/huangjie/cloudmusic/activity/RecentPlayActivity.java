package com.example.huangjie.cloudmusic.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.huangjie.cloudmusic.R;

import butterknife.BindView;

/**
 * Created by huangjie on 2016/9/17.
 */
public class RecentPlayActivity extends BaseActivity {
    @BindView(R.id.id_recentpaly_recyclerview)
     RecyclerView mRecylerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recent_play);



    }

}
