package com.example.huangjie.cloudmusic.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.global.CloudMusicApplication;

/**
 * Created by huangjie on 2016/8/23.
 */
public class MorePopWindow extends PopupWindow {
    private View mRootView;
    private MusicBean mMusicBean;

    public MorePopWindow(Context context) {
        super(context);
        init();
    }

    /**
     * 设置数据
     *
     * @param musicBean
     */
    public void setData(MusicBean musicBean) {
        this.mMusicBean = musicBean;
    }

    /**
     * 初始化
     */
    private void init() {
        mRootView = LayoutInflater.from(CloudMusicApplication.getContext()).inflate(R.layout.more_popwindow, null, false);
        this.setContentView(mRootView);
    }

}
