package com.example.huangjie.cloudmusic.view;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.bean.SlideItem;
import com.example.huangjie.cloudmusic.fragment.HomeFragment;
import com.example.huangjie.cloudmusic.fragment.MusicFragment;
import com.example.huangjie.cloudmusic.fragment.MusicMenuFragment;
import com.example.huangjie.cloudmusic.utils.CommonAdapter;
import com.example.huangjie.cloudmusic.utils.Utils;
import com.example.huangjie.cloudmusic.utils.ViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mMusicIcon;
    private ImageView mSuggestIcon;
    private ImageView mSourseIcon;
    private DrawerLayout mDraerLayout;
    private Toolbar mToolBar;
    private ListView mListView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private CommonAdapter<SlideItem> mAdater;
    private ArrayList<SlideItem> mDataList;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();

    }


    /**
     * 初始化事件
     */
    private void initEvent() {
        mSourseIcon.setOnClickListener(this);
        mMusicIcon.setOnClickListener(this);
        mSuggestIcon.setOnClickListener(this);
        mListView.setAdapter(mAdater = new CommonAdapter<SlideItem>(this, mDataList, R.layout.drawerlayout_list_item) {
            @Override
            public void convert(ViewHolder viewHolder, SlideItem data) {
                viewHolder.setImageDrawable(R.id.id_left_listitem_iv, data.getIcon());
                viewHolder.setTextView(R.id.id_left_listitem_tv, data.getTitle());
                if (!TextUtils.isEmpty(data.getmDesc())) {
                    viewHolder.setTextView(R.id.id_left_listitem_desc, data.getmDesc());
                }
                if (data.getmShowStatus()) {
                    viewHolder.getViewById(R.id.id_left_listitem_switch).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 初始化view
     */
    private void initView() {
        mSuggestIcon = (ImageView) findViewById(R.id.id_main_suggest);
        mMusicIcon = (ImageView) findViewById(R.id.id_main_music);
        mSourseIcon = (ImageView) findViewById(R.id.id_main_me);
        mListView = (ListView) findViewById(R.id.id_drawerlayout_listView);
        mDraerLayout = (DrawerLayout) findViewById(R.id.id_main_drawerLayout);
        mToolBar = (Toolbar) findViewById(R.id.id_main_toolbar);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDraerLayout, mToolBar, R.string.open, R.string.close);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeButtonEnabled(false);
        // mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        //  mToolBar.setNavigationIcon(getResources().getDrawable(R.drawable.actionbar_menu));
        // mActionBarDrawerToggle.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.actionbar_menu));
        mDraerLayout.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        mSuggestIcon.setImageDrawable(Utils.getDrawable(R.drawable.actionbar_discover_selected));

        getSupportFragmentManager().beginTransaction().replace(R.id.id_main_container, new HomeFragment()).commit();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //目的在于让 DrawerLayout 和 ActionBarDrawerToggle 的状态同步
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View view) {
        resetIcon();
        changeFragment(view.getId());
        switch (view.getId()) {
            case R.id.id_main_music:
                mMusicIcon.setImageDrawable(Utils.getDrawable(R.drawable.actionbar_music_selected));
                break;
            case R.id.id_main_me:
                mSourseIcon.setImageDrawable(Utils.getDrawable(R.drawable.actionbar_friends_selected));
                break;
            case R.id.id_main_suggest:
                mSuggestIcon.setImageDrawable(Utils.getDrawable(R.drawable.actionbar_discover_selected));
                break;
            case R.id.id_alert_cancel:
                mAlertDialog.cancel();
                break;
            case R.id.id_alert_confirm:
                finish();
                break;
        }
    }


    /**
     * 根据id改变fragment
     */
    public void changeFragment(int layoutid) {
        switch (layoutid) {
            case R.id.id_main_music:
                getSupportFragmentManager().beginTransaction().replace(R.id.id_main_container, new MusicFragment()).commit();
                break;
            case R.id.id_main_suggest:
                getSupportFragmentManager().beginTransaction().replace(R.id.id_main_container, new HomeFragment()).commit();

                break;
            case R.id.id_main_me:
                getSupportFragmentManager().beginTransaction().replace(R.id.id_main_container, new MusicMenuFragment()).commit();
                break;
        }
    }


    /**
     * 必须从写此方法，否则导致点击ActionBarToggle无法弹出DrawerLayout
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 重置图标
     */
    private void resetIcon() {
        mSuggestIcon.setImageDrawable(Utils.getDrawable(R.drawable.actionbar_discover_normal));
        mMusicIcon.setImageDrawable(Utils.getDrawable(R.drawable.actionbar_music_normal));
        mSourseIcon.setImageDrawable(Utils.getDrawable(R.drawable.actionbar_friends_normal));
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mDataList = new ArrayList<>();
        SlideItem slideItem = new SlideItem("我的消息", Utils.getDrawable(R.drawable.lay_icn_msg), "", false);
        mDataList.add(slideItem);
        SlideItem slideItem2 = new SlideItem("积分商城", Utils.getDrawable(R.drawable.lay_icn_similar), "10积分", false);
        mDataList.add(slideItem2);
        SlideItem slideItem3 = new SlideItem("会员中心", Utils.getDrawable(R.drawable.topmenu_icn_vip), "", false);
        mDataList.add(slideItem3);
        SlideItem slideItem4 = new SlideItem("听歌识曲", Utils.getDrawable(R.drawable.lay_icn_recording), "", false);
        mDataList.add(slideItem4);
        SlideItem slideItem5 = new SlideItem("主题换肤", Utils.getDrawable(R.drawable.topmenu_icn_skin), "官方红", false);
        mDataList.add(slideItem5);
        SlideItem slideItem6 = new SlideItem("夜间模式", Utils.getDrawable(R.drawable.lay_icn_document), "", true);
        mDataList.add(slideItem6);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.alertdialog_view, null, false);
        Button btnCancel = (Button) view.findViewById(R.id.id_alert_cancel);
        Button btnConfirm = (Button) view.findViewById(R.id.id_alert_confirm);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        builder.setView(view);
        mAlertDialog = builder.create();
        //获取AlertDialog的WindowManager.LayoutParams
        Window window = mAlertDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.9f;

        //获取屏幕宽度
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int windowWidth = displayMetrics.widthPixels;
        //设置透明度
        lp.width = (int) (windowWidth * 0.8);

        window.setAttributes(lp);
        mAlertDialog.show();


    }
}
