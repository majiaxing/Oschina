package com.example.lenovo.oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.oschina.base.BaseFragment;

import java.util.List;

/**
 * Created by admin on 2017/4/12.
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter{
    private List<BaseFragment>  fragments;
    private List<String>  titles;
    public NewsPagerAdapter(FragmentManager fm, List<BaseFragment>  fragments, List<String>  titles) {
            super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
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
