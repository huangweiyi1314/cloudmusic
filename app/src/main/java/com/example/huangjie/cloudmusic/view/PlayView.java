package com.example.huangjie.cloudmusic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.huangjie.cloudmusic.R;

/**
 * Created by huangjie on 2016/9/17.
 */
public class PlayView extends View {
    private Drawable mIcon;
    private Bitmap mDisIcon;
    private int mWidth;

    public PlayView(Context context) {
        this(context, null);
    }

    public PlayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayView);
        mIcon = typedArray.getDrawable(R.styleable.PlayView_view_icon);
        mWidth = typedArray.getInteger(R.styleable.PlayView_view_width, 120);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        BitmapFactory.Options options = new BitmapFactory.Options();
        mDisIcon = BitmapFactory.decodeResource(getResources(), R.drawable.play_disc, options);
        // options.inJustDecodeBounds = true;
        int rateX = Math.round(options.outWidth * 1.0f / (getMeasuredWidth()));
        int rateY = Math.round(mDisIcon.getHeight() * 1.0f / (getMeasuredHeight()));
        Log.i("huangjie", mDisIcon.getWidth() + "这是图片宽度" + getMeasuredWidth() + "测量宽度");
        int rate = Math.min(rateX, rateY);
        options.inJustDecodeBounds = false;
        options.inSampleSize = rate;
        mDisIcon = BitmapFactory.decodeResource(getResources(), R.drawable.play_disc, options);
        mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawBitmap(mDisIcon, (getMeasuredWidth() - mDisIcon.getWidth()) / 2, (getMeasuredHeight() - mDisIcon.getHeight()) / 2, paint);
        Log.i("huangjie", mDisIcon.getWidth() + "这是图片宽度11111" + getMeasuredWidth() + "测量宽度");
        Bitmap icon = getRoundBitmap();
        canvas.drawBitmap(icon, (getMeasuredWidth() - mWidth) / 2, (getMeasuredHeight() - mWidth) / 2, null);
    }


    /**
     * 获取圆角图片
     *
     * @return
     */
    private Bitmap getRoundBitmap() {
        Bitmap target = Bitmap.createBitmap(mWidth, mWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        Bitmap srouce = BitmapFactory.decodeResource(getResources(), R.drawable.test, option);
        int rateX = Math.round(option.outWidth * 1.0f / mWidth);
        int rateY = Math.round(option.outHeight * 1.0f / mWidth);
        int rate = Math.max(rateX, rateY);
        option.inJustDecodeBounds = false;
        option.inSampleSize = rate;
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.test, option);
        canvas.drawBitmap(icon, 0, 0, paint);
        return target;
    }
}
