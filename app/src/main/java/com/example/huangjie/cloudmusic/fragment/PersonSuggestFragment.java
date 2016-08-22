package com.example.huangjie.cloudmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.constant.Constant;
import com.example.huangjie.cloudmusic.utils.Utils;
import com.example.huangjie.cloudmusic.view.DateView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huangjie on 2016/8/18.
 */
public class PersonSuggestFragment extends Fragment {
    private ViewPager mViewPager;
    private ImageView[] mPointer;
    private DateView mDateView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);
            mHandler.sendEmptyMessageDelayed(0x11, 2000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_personsuggest, container, false);

        initView(view);
        initEvent();
        initData();

        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String day = dateFormat.format(date);
        mDateView.setDate(day);
    }

    /**
     * 初始化shijian
     */
    private void initEvent() {
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ViewPager viewPager = (ViewPager) v;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                reset();
                mPointer[position % Constant.FRAGMENT_SUGGEST_VIEWPAGERCOUNT].setImageDrawable(Utils.getDrawable(R.drawable.pointer_select));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化View
     *
     * @param view
     */
    private void initView(final View view) {
        mPointer = new ImageView[4];
        mPointer[0] = (ImageView) view.findViewById(R.id.id_personsuggest_pointOne);
        mPointer[1] = (ImageView) view.findViewById(R.id.id_personsuggest_pointTwo);
        mPointer[2] = (ImageView) view.findViewById(R.id.id_personsuggest_pointThree);
        mPointer[3] = (ImageView) view.findViewById(R.id.id_personsuggest_pointFour);
        mViewPager = (ViewPager) view.findViewById(R.id.id_personsuggest_viewpager);
        mDateView = (DateView) view.findViewById(R.id.id_personsuggest_DateView);

        final int[] pictures = new int[]{R.drawable.index_new_america, R.drawable.index_new_china,
                R.drawable.index_new_japan, R.drawable.index_new_korea};
        //为ViewPager设置适配器
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public ImageView instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(Utils.getContext());
                imageView.setImageDrawable(Utils.getDrawable(pictures[position % pictures.length]));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        mHandler.sendEmptyMessageDelayed(0x11, 2000);
    }

    /**
     * 让所有的指示器成为默认状态
     */
    public void reset() {
        for (ImageView imageView : mPointer) {
            imageView.setImageDrawable(Utils.getDrawable(R.drawable.pointer_mormal));
        }
    }
}
