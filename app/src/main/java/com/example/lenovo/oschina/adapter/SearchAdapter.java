package com.example.lenovo.oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.oschina.base.BaseFragment;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/21.
 */
public class SearchAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> fragments;
    private List<String>  titles;

    public SearchAdapter(FragmentManager fm,List<BaseFragment>  fragments,List<String>  titles) {
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
