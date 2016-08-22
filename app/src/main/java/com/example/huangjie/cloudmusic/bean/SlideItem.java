package com.example.huangjie.cloudmusic.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by huangjie on 2016/8/18.
 */
public class SlideItem {
    private String title;
    private Drawable icon;
    private String mDesc;
    private boolean mShowStatus;

    public  SlideItem(){

    }
    public SlideItem(String title, Drawable icon, String mDesc, boolean mShowStatus) {
        this.title = title;
        this.icon = icon;
        this.mDesc = mDesc;
        this.mShowStatus = mShowStatus;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public void setmShowStatus(boolean mShowStatus) {
        this.mShowStatus = mShowStatus;
    }

    public String getmDesc() {
        return mDesc;
    }

    public boolean getmShowStatus() {
        return mShowStatus;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
