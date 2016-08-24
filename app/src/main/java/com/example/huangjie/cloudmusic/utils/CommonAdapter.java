package com.example.huangjie.cloudmusic.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/**
 * Created by huangjie on 2016/8/14.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private ArrayList<T> mData;
    private Context mContext;
    private int mLayoutid;

    public CommonAdapter(Context context, ArrayList<T> data, int layoutid) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutid = layoutid;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.getInstance(mContext, mLayoutid, parent, convertView, position);
        convert(viewHolder, mData.get(position),position);
        return viewHolder.getConvertView();
    }

    /**
     * 为ViewHolder中的View 赋值
     *
     * @param viewHolder
     * @param data
     */
    public abstract void convert(ViewHolder viewHolder, T data,int position);

}
