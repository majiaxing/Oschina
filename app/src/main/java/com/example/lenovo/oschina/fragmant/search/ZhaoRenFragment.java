package com.example.lenovo.oschina.fragmant.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.ZhaoRenAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.ZhaoRenBean;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2017/4/20.
 */

public class ZhaoRenFragment extends BaseFragment{
    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    private INewsModel newsModel;
    private SharedPreferences mShared;
    private String name;
    private List<ZhaoRenBean.UserBean> mList;
    private ZhaoRenAdapter mAdapter;
    private int Index=1;

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
        mShared=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        name = mShared.getString("name", "");
    }

    @Override
    protected void initData() {
        newsModel=new NewsModelImp();
        mList=new ArrayList<>();
        mAdapter=new ZhaoRenAdapter(getActivity().getApplicationContext(),mList);
        PullToRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
    newsModel.ZhaoRen(name, new NetWorkCallBack() {
        @Override
        public void onSuccess(String xmlData) {
            XStream xStream=new XStream();
            xStream.alias("oschina",ZhaoRenBean.class);
            xStream.alias("user",ZhaoRenBean.UserBean.class);
            ZhaoRenBean  zhaoRenBean    = (ZhaoRenBean) xStream.fromXML(xmlData);
            mList.addAll(zhaoRenBean.getUsers());
            mAdapter.notifyDataSetChanged();
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
