package com.example.lenovo.oschina.fragmant.kaiyuanruanjian;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/25.
 */

public class ZongFenleiFragment extends BaseFragment {


    @BindView(R.id.Main_FrameLayout)
    android.widget.FrameLayout FrameLayout;
    Unbinder unbinder;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected int layoutId() {
        return R.layout.framelayout;
    }

    @Override
    protected void initView(View view) {

        fragmentManager= App.activity.getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();

        transaction.replace(R.id.Main_FrameLayout,new FenLeiZongFragment(),FenLeiZongFragment.class.getSimpleName());
        transaction.commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

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
}
