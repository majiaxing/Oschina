package com.example.lenovo.oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.oschina.base.BaseFragment;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */

public class DongTanFragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private List<String>  titles;
    public DongTanFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
