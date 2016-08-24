package com.example.huangjie.cloudmusic.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.global.CloudMusicApplication;

/**
 * Created by huangjie on 2016/8/23.
 */
public class MorePopWindow extends PopupWindow implements View.OnClickListener {
    private View mRootView;
    private MusicBean mMusicBean;
    private IPlayNext mPlaynext;
    private IShareSong mShareSong;
    private IdeleteSong mDeleteSong;

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
     * 设置删除回掉接口
     *
     * @param delete
     */
    public void setDelet(IdeleteSong delete) {
        this.mDeleteSong = delete;
        TextView textView = (TextView) mRootView.findViewById(R.id.id_popwindow_songname);
        if (mMusicBean!=null) {
            textView.setText(mMusicBean.getName());
        }
    }

    /**
     * 设置分享回掉接口
     */
    public void setShare(IShareSong share) {
        this.mShareSong = share;
    }

    /**
     * 播放下一首回掉接口
     *
     * @param next
     */
    public void setPlayNext(IPlayNext next) {
        this.mPlaynext = next;
    }

    /**
     * 初始化
     */
    private void init() {
        mRootView = LayoutInflater.from(CloudMusicApplication.getContext()).inflate(R.layout.more_popwindow, null, false);
        this.setContentView(mRootView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.AnimationFade);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        LinearLayout mPlayNext = (LinearLayout) mRootView.findViewById(R.id.id_popwindow_next);
        LinearLayout mShare = (LinearLayout) mRootView.findViewById(R.id.id_popwindow_share);
        LinearLayout mDelete = (LinearLayout) mRootView.findViewById(R.id.id_popwindow_delete);

        mPlayNext.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_popwindow_next:
                mPlaynext.nect();
                break;
            case R.id.id_popwindow_share:
                mShareSong.share(mMusicBean);
                break;
            case R.id.id_popwindow_delete:
                mDeleteSong.delete(mMusicBean);
                break;

        }
    }


    /**
     * 播放下一首回掉接口
     */
    public interface IPlayNext {
        void nect();
    }

    /**
     * 分享歌曲接口
     */
    public interface IShareSong {
        void share(MusicBean bean);
    }

    /**
     * 删除歌曲回掉接口
     */
    public interface IdeleteSong {
        void delete(MusicBean bean);
    }

}
