package com.example.lenovo.oschina.activity.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.FragmantAdapter;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.fragmant.BoKeFragmant;
import com.example.lenovo.oschina.fragmant.HuodongFragmant;
import com.example.lenovo.oschina.fragmant.WendaFragmant;
import com.example.lenovo.oschina.fragmant.ZixunFragmant;
import com.example.lenovo.oschina.fragmant.mine.MyFragment;
import com.example.lenovo.oschina.fragmant.mine.PinglunFragment;
import com.example.lenovo.oschina.fragmant.mine.SiXinFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2017/5/25.
 */
public class XiaoxiActivity extends BaseActivity {
    @BindView(R.id.Fanhui)
    ImageView Fanhui;
    @BindView(R.id.Dongtan_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.Dongtan_ViewPager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mList = new ArrayList<>();
    private ArrayList<String> mNameList = new ArrayList<>();
    private MyFragment aFragmant;
    private PinglunFragment bFragmant;
    private SiXinFragment cFragmant;
    private FragmentPagerAdapter mAdapter;
    @Override
    protected int getlayoutId() {
        return R.layout.xiaoxi_activity;
    }

    @Override
    protected void init() {
        aFragmant = new MyFragment();
        bFragmant = new PinglunFragment();
        cFragmant = new SiXinFragment();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        mList.add(aFragmant);
        mList.add(bFragmant);
        mList.add(cFragmant);
        mNameList.add("@我");
        mNameList.add("评论");
        mNameList.add("私信");
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mAdapter = new FragmantAdapter(getSupportFragmentManager(), mList, mNameList);
        mViewPager.setAdapter(mAdapter);
        mTablayout.addTab(mTablayout.newTab().setText(mNameList.get(0)));
        mTablayout.addTab(mTablayout.newTab().setText(mNameList.get(1)));
        mTablayout.addTab(mTablayout.newTab().setText(mNameList.get(2)));
        mTablayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Fanhui)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Fanhui:
                onBackPressed();
                break;

        }
    }
}
