package com.example.lenovo.oschina.activity.faxian;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.NewsPagerAdapter;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.fragmant.faxian.TuiJieFragment;
import com.example.lenovo.oschina.fragmant.kaiyuanruanjian.FenLeiZongFragment;
import com.example.lenovo.oschina.fragmant.kaiyuanruanjian.GuoChanFragment;
import com.example.lenovo.oschina.fragmant.kaiyuanruanjian.ReMenFragment;
import com.example.lenovo.oschina.fragmant.kaiyuanruanjian.ZongFenleiFragment;
import com.example.lenovo.oschina.fragmant.kaiyuanruanjian.ZuiXinFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Lenovo on 2017/5/20.
 */
public class FenLeiActivity extends BaseActivity{

    @BindView(R.id.KaiYuanTablayut)
    TabLayout KaiYuanTablayut;
    @BindView(R.id.KaiYuanViewPager)
    ViewPager KaiYuanViewPager;
    private List<String> titles;
    private List<BaseFragment> fragment;
    private NewsPagerAdapter pagerAdapter;
    @Override
    protected int getlayoutId() {
        return R.layout.kaiyuan;
    }

    @Override
    protected void init() {
        App.activity=this;
        titles=new ArrayList<>();
        titles.add("分类");
        titles.add("推荐");
        titles.add("最新");
        titles.add("热门");
        titles.add("国产");
        fragment=new ArrayList<>();
        fragment.add(new ZongFenleiFragment());
        fragment.add(new KYRJTuiJieFragment());
        fragment.add(new ZuiXinFragment());
        fragment.add(new ReMenFragment());
        fragment.add(new GuoChanFragment());
        pagerAdapter=new NewsPagerAdapter(getSupportFragmentManager(),fragment,titles);
        KaiYuanViewPager.setAdapter(pagerAdapter);
        KaiYuanTablayut.setupWithViewPager(KaiYuanViewPager);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
}
