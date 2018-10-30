package com.example.lenovo.oschina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.faxian.FenLeiActivity;
import com.example.lenovo.oschina.activity.faxian.XianXiaActivity;
import com.example.lenovo.oschina.activity.faxian.YaoYiYaoActivity;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.fragmant.faxian.TuiJieFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/19.
 */

public class FaxianActivity extends BaseFragment {

    @BindView(R.id.MyYunTuiJie)
    RelativeLayout MyYunTuiJie;
    @BindView(R.id.KaiyuanRuanjian)
    RelativeLayout KaiyuanRuanjian;
    @BindView(R.id.SaoyiSao)
    RelativeLayout SaoyiSao;
    @BindView(R.id.YaoyiYao)
    RelativeLayout YaoyiYao;
    @BindView(R.id.FuJinChengXuYuan)
    RelativeLayout FuJinChengXuYuan;
    @BindView(R.id.event)
    ImageView event;
    @BindView(R.id.XianXiaHuoDong)
    RelativeLayout XianXiaHuoDong;
    @BindView(R.id.home_sousuo)
    Button homeSousuo;
    @BindView(R.id.Git)
    ImageView Git;
    @BindView(R.id.Soft)
    ImageView Soft;
    @BindView(R.id.scan)
    ImageView scan;
    @BindView(R.id.shake)
    ImageView shake;
    @BindView(R.id.nearby)
    ImageView nearby;
    Unbinder unbinder;

    @Override
    protected int layoutId() {
        return R.layout.discoverfragment;
    }

    @Override
    protected void initView(View view) {

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

    @OnClick({R.id.MyYunTuiJie, R.id.KaiyuanRuanjian, R.id.SaoyiSao, R.id.YaoyiYao, R.id.FuJinChengXuYuan, R.id.XianXiaHuoDong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MyYunTuiJie:

                Intent intent = new Intent(getContext(), TuiJieFragment.class);
                startActivity(intent);
                break;
            case R.id.KaiyuanRuanjian:
                Intent intent1 = new Intent(getActivity().getApplication(), FenLeiActivity.class);
                startActivity(intent1);
                break;
            case R.id.SaoyiSao:

                break;
            case R.id.YaoyiYao:
                Intent intent2 = new Intent(App.activity, YaoYiYaoActivity.class);
                startActivity(intent2);
                break;
            case R.id.FuJinChengXuYuan:
                break;
            case R.id.XianXiaHuoDong:
                Intent intent3 = new Intent(App.activity, XianXiaActivity.class);
                startActivity(intent3);
                break;
        }
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
