package com.example.huangjie.cloudmusic.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.MusicBean;
import com.example.huangjie.cloudmusic.utils.Utils;

/**
 * Created by huangjie on 2016/9/17.
 */
public class MoreBottomSheetDialog extends BottomSheetDialog implements View.OnClickListener {
    private View mRootView;
    private MusicBean mMusicBean;
    private IPlayNext mPlaynext;
    private IShareSong mShareSong;
    private IdeleteSong mDeleteSong;

    public MoreBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public MoreBottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected MoreBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 设置ContentView
     */
    public void setRootView(int layoutid) {
        mRootView = Utils.inflate(layoutid);
        initEvent();
        super.setContentView(mRootView);
    }


    /**
     * 初始化事件
     */
    private void initEvent() {
        mRootView.findViewById(R.id.id_popwindow_next).setOnClickListener(this);
        mRootView.findViewById(R.id.id_popwindow_collect).setOnClickListener(this);
        mRootView.findViewById(R.id.id_popwindow_share).setOnClickListener(this);
        mRootView.findViewById(R.id.id_popwindow_delete).setOnClickListener(this);
    }

    /**
     * 设置数据
     *
     * @param musicBean
     */
    public void setData(MusicBean musicBean) {
        this.mMusicBean = musicBean;
        TextView textView = (TextView) mRootView.findViewById(R.id.id_popwindow_songname);
        textView.setText(musicBean.getName());

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
     * 设置删除接口回掉
     *
     * @param delete
     */
    public void setDelete(IdeleteSong delete) {
        this.mDeleteSong = delete;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_popwindow_share:
                break;
            case R.id.id_popwindow_next:
                Log.i("huangjie","执行了");
                mPlaynext.next();
                break;
            case R.id.id_popwindow_delete:
                break;
            case R.id.id_popwindow_collect:
                break;

        }
    }

    /**
     * 播放下一首回掉接口
     */
    public interface IPlayNext {
        void next();
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
