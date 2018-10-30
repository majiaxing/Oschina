package com.example.lenovo.oschina.fragmant.sosuo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.SearchAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.fragmant.search.BokeFragment;
import com.example.lenovo.oschina.fragmant.search.RuanJianFragment;
import com.example.lenovo.oschina.fragmant.search.WenDaFragment;
import com.example.lenovo.oschina.fragmant.search.ZhaoRenFragment;
import com.example.lenovo.oschina.fragmant.search.ZiXunFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/21.
 */
public class SearchFragment extends BaseFragment {

    @BindView(R.id.Search_Tabl)
    TabLayout SearchTabl;
    @BindView(R.id.Search_Viewpager)
    ViewPager SearchViewpager;
    Unbinder unbinder;

    private List<String> titles;
    private List<BaseFragment> fragment;
    private SearchAdapter pagerAdapter;

    @Override
    protected int layoutId() {
        return R.layout.search;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        titles = new ArrayList<>();
        titles.add("软件");
        titles.add("博客");
        titles.add("问答");
        titles.add("咨询");
        titles.add("找人");

        fragment = new ArrayList<>();
        fragment.add(new RuanJianFragment());
        fragment.add(new BokeFragment());
        fragment.add(new WenDaFragment());
        fragment.add(new ZiXunFragment());
        fragment.add(new ZhaoRenFragment());
        pagerAdapter = new SearchAdapter(getFragmentManager(), fragment, titles);
        SearchViewpager.setAdapter(pagerAdapter);
        SearchTabl.setupWithViewPager(SearchViewpager);
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
