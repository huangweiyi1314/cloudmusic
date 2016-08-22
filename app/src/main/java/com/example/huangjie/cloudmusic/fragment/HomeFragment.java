package com.example.huangjie.cloudmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangjie.cloudmusic.R;

/**
 * Created by huangjie on 2016/8/18.
 */
public class HomeFragment extends Fragment {
    private static final int VIEWPAGERR_COUNT = 4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        final String titles[] = new String[]{"个性推荐", "歌单", "影单", "AV"};
        ViewPager viewpager = (ViewPager) view.findViewById(R.id.id_fragment_home_viewpager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.id_fragment_home_tablayout);
        viewpager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    PersonSuggestFragment personFragment = new PersonSuggestFragment();
                    return personFragment;
                } else if (position == 1) {
                    MusicMenuFragment musicFragment = new MusicMenuFragment();

                } else if (position == 2) {
                    MovieMenuFragment movieFragment = new MovieMenuFragment();
                    return movieFragment;
                } else if (position == 3) {
                    AVMenuFragment avFragment = new AVMenuFragment();
                    return avFragment;
                }
                return new MusicMenuFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return VIEWPAGERR_COUNT;
            }
        });
        tabLayout.setupWithViewPager(viewpager);
    }
}
