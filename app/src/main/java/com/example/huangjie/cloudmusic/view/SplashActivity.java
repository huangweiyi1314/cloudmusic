package com.example.huangjie.cloudmusic.view;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.example.huangjie.cloudmusic.R;
import com.example.huangjie.cloudmusic.utils.SharePreferenceUtils;

/**
 * Created by huangjie on 2016/8/18.
 */
public class SplashActivity extends AppCompatActivity {
    private LinearLayout mLinearAbout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_splash_view);
        mLinearAbout = (LinearLayout) findViewById(R.id.id_linearlayout_about);
        mLinearAbout.setVisibility(View.GONE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enterMainActivity();
            }

        }, 500);
    }

    /**
     * 进入MainActivity
     */
    private void enterMainActivity() {
        mLinearAbout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, MainActivity.class);
        SharePreferenceUtils.putPlayStatus(false);
        startActivity(intent);
        finish();
    }


}
