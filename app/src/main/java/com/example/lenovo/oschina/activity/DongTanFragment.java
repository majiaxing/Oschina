package com.example.lenovo.oschina.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.FragmantAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.fragmant.dongtan.MineFragment;
import com.example.lenovo.oschina.fragmant.dongtan.RemenFragment;
import com.example.lenovo.oschina.fragmant.dongtan.ZuixinFragmant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/15.
 */

public class DongtanFragment extends BaseFragment  {


    @BindView(R.id.home_sousuo)
    Button homeSousuo;
    Unbinder unbinder;
    private TabLayout mTabLayout;
    private ViewPager mviewPager;
    private ArrayList<Fragment> mList = new ArrayList<>();

    private ArrayList<String> mNameList = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;


    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.Dongtan_tablayout);
        mviewPager = (ViewPager) view.findViewById(R.id.Dongtan_ViewPager);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        mList.add(new ZuixinFragmant());
        mList.add(new RemenFragment());
        mList.add(new ZuixinFragmant());
        mList.add(new MineFragment());
        mNameList.add("最新动弹");
        mNameList.add("热门动弹");
        mNameList.add("每日乱弹");
        mNameList.add("我的动弹");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mAdapter = new FragmantAdapter(getFragmentManager(), mList, mNameList);
        mviewPager.setAdapter(mAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(mNameList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mNameList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mNameList.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mNameList.get(3)));
        mTabLayout.setupWithViewPager(mviewPager);

    }


    @Override
    public void setParams(Bundle bundle) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.home_sousuo)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivity(intent);
    }
    @Override
    public void onShow() {
        super.onShow();
        App.MainRadioGroup.setVisibility(View.VISIBLE);
    }





}
