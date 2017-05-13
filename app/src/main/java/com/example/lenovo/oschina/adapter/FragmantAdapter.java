package com.example.lenovo.oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/2/20.
 */

public class FragmantAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mList;
    private ArrayList<String> mNameList;

    public FragmantAdapter(FragmentManager fm , ArrayList<Fragment> mList, ArrayList<String> mNameList) {
        super(fm);
        this.mList = mList;
        this.mNameList = mNameList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.isEmpty()?0:mList.size();
    }

    public CharSequence getPageTitle(int position){
    return  mNameList.get(position);
    }

}
