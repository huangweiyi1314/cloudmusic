package com.example.huangjie.cloudmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangjie.cloudmusic.R;

/**
 * Created by huangjie on 2016/8/18.
 */
public class AVMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.layout_fragment_av,container,false);
        return view;
    }
}
