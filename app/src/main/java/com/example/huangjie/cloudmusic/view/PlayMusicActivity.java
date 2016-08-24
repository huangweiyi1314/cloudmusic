package com.example.huangjie.cloudmusic.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.huangjie.cloudmusic.R;

/**
 * Created by huangjie on 2016/8/23.
 */
public class PlayMusicActivity extends AppCompatActivity {
   private Toolbar mToolBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_play_music);
        initView();
        if (Build.VERSION.SDK_INT>=21){
            View view = getWindow().getDecorView();
            int option =View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            view.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar!=null) {
                actionBar.hide();
            }
        }
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.id_playmusic_toolbar);
    }
}
