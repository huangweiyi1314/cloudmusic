package com.example.huangjie.cloudmusic.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huangjie.cloudmusic.bean.MusicBean;


/**
 * Created by huangjie on 2016/8/14.
 */
public class ViewHolder {
    private SparseArray<View> mViewArray;//存储viewholder中的所有的view
    private int mLayoutid;//item布局layoutid
    private View mRootView;//相当于ConvertView
    private Context mContext;
    private int position;
    /**
     * @param context
     * @param layoutid
     * @param parent
     */
    public ViewHolder(Context context, int layoutid, ViewGroup parent, int position) {
        mContext = context;
        mLayoutid = layoutid;
        this.position = position;
        mViewArray = new SparseArray<View>();
        mRootView = LayoutInflater.from(context).inflate(mLayoutid, parent, false);
        mRootView.setTag(this);

    }

    /**
     * 获取viewholder对象
     *
     * @return
     */
    public static ViewHolder getInstance(Context context, int layoutid, ViewGroup parent, View convertView, int poition) {
        if (convertView == null) {
            ViewHolder viewHolder = new ViewHolder(context, layoutid, parent, poition);
            return viewHolder;
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    /**
     * 获取convertview
     *
     * @return
     */
    public View getConvertView() {
        return mRootView;
    }


    /**
     * 获取view 根据viewid
     *
     * @param viewid
     * @return
     */
    public View getViewById(int viewid) {
        View view = mViewArray.get(viewid);
        if (view == null) {
            view = getConvertView().findViewById(viewid);
            mViewArray.put(viewid, view);
        }
        return view;
    }

    /**
     * 给ImageView设置drawable
     *
     * @param viewid
     * @param drawable
     */
    public void setImageDrawable(int viewid, Drawable drawable) {
        ImageView imageView = (ImageView) getViewById(viewid);
        imageView.setImageDrawable(drawable);
    }



    /**
     * 设置textView text
     *
     * @param viewid
     * @param text
     */
    public void setTextView(int viewid, String text) {
        TextView textView = (TextView) getViewById(viewid);
        textView.setText(text);
    }

}
