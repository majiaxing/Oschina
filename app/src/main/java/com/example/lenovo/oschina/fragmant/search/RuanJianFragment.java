package com.example.lenovo.oschina.fragmant.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;


import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.fragmant.FindAdapter;
import com.example.lenovo.oschina.modle.enitity.SearchBean;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2017/4/17.
 */

public class RuanJianFragment extends BaseFragment{

    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    private List<SearchBean.ResultBean> mList;
    private INewsModel newsModel;
    private FindAdapter mAdapter;
    private SharedPreferences mShared;
    private int Index=1;
    private String name;

    @Override
    protected int layoutId() {
        return R.layout.pullrecycleview;
    }

    @Override
    protected void initView(View view) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        PullToRecycleView.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        PullToRecycleView.setLayoutManager(layoutManager);
        //是否开启上拉加载功能
        PullToRecycleView.setLoadingMoreEnabled(true);
        //开启刷新回调
        PullToRecycleView.displayLastRefreshTime(true);
        PullToRecycleView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                PullToRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PullToRecycleView.setRefreshComplete();
                        mList.clear();
                        initData();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                PullToRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PullToRecycleView.setLoadMoreComplete();
                        Index++;
                        loadData();
                    }
                },2000);
            }
        });
    }

    @Override
    protected void initData() {
        newsModel=new NewsModelImp();
        mList=new ArrayList<>();

        mShared=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        name = mShared.getString("name", "");
        Log.i("name",name);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        newsModel.search("software", name, Index, new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {
                XStream xStream=new XStream();
                xStream.alias("oschina",SearchBean.class);
                xStream.alias("result",SearchBean.ResultBean.class);
                SearchBean searchBean= (SearchBean) xStream.fromXML(xmlData);
                mList.addAll(searchBean.getResults());
                mAdapter=new FindAdapter(getActivity().getApplicationContext(),mList);
                PullToRecycleView.setAdapter(mAdapter);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void setParams(Bundle bundle) {

    }
}
