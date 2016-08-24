package com.example.huangjie.cloudmusic.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by huangjie on 2016/8/22.
 */
public class MusicBean  implements Serializable{
    private int id;
    private String name;
    private String special;
    private String songerName;
    private String url;
    private float duration;
    private String size;
    private Bitmap mIcon;

    public void setmIcon(Bitmap mIcon) {
        this.mIcon = mIcon;
    }

    public Bitmap getmIcon() {
        return mIcon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecial() {
        return special;
    }

    public String getSongerName() {
        return songerName;
    }

    public String getUrl() {
        return url;
    }

    public float getduration() {
        return duration;
    }

    public String getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setSongerName(String songerName) {
        this.songerName = songerName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setduration(float duration) {
        this.duration = duration;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", special='" + special + '\'' +
                ", songerName='" + songerName + '\'' +
                ", url='" + url + '\'' +
                ", duration=" + duration +
                ", size='" + size + '\'' +
                ", mIcon=" + mIcon +
                '}';
    }
}
