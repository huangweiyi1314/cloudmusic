package com.example.huangjie.cloudmusic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.example.huangjie.cloudmusic.R;

/**
 * Created by huangjie on 2016/8/20.
 */
public class DateView extends View {
    private String mDate = "20";
    private int mColor;

    public DateView(Context context) {
        this(context, null);
    }

    public DateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DateView);
        mDate = typedArray.getString(R.styleable.DateView_view_text);
        mColor = typedArray.getColor(R.styleable.DateView_view_color, 0xFF0000);
        typedArray.recycle();
    }

    /**
     * 设置Date
     */
    public void setDate(String date) {
        this.mDate = date;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆环
        int iconwidth = getWidth() / 2;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mColor);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(4);
        canvas.drawCircle(iconwidth, iconwidth, iconwidth - 2, paint);
        //绘制字体
        Paint textPaint = new Paint();
        textPaint.setColor(mColor);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setTextSize(60);
        textPaint.setStrokeWidth(4);
        Rect textBound = new Rect();

        textPaint.getTextBounds(mDate, 0, mDate.length(), textBound);//获取字体大小范围
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() + fontMetricsInt.top - fontMetricsInt.bottom) / 2
                - fontMetricsInt.top;
        int left = (getMeasuredWidth() - textBound.width()) / 2;
        canvas.drawText(mDate, left, baseline, textPaint);
    }
}
