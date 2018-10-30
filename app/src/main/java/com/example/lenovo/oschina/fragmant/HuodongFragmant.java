package com.example.lenovo.oschina.fragmant;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.zixunxiangqing.BokeXiangqing;
import com.example.lenovo.oschina.adapter.BokeListViewAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.coefig.ThreadUtils;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Lenovo on 2017/5/9.
 */

public class HuodongFragmant extends BaseFragment implements NetWorkCallBack,AdapterView.OnItemClickListener{
    private ListView mlistView;
    private List<ItemBoke.BlogBean> mList = new ArrayList<>();
    private BokeListViewAdapter bokelistViewAdapter;
    private PtrFrameLayout ptrFrameLayout;
    private int pageIndex = 0;

    @Override
    protected int layoutId() {
        return R.layout.huodongviewpager_activity;
    }

    @Override
    protected void initView(View view) {

        mlistView = (ListView) view.findViewById(R.id.huodong_ListView);
        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.huodong_ptr);
    }

    @Override
    protected void initData() {
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(getContext());
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.setFooterView(footer);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.addPtrUIHandler(header);

        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                Log.e("MainActivity", "开始加载更多");

                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ptrFrameLayout.refreshComplete();
                                loadData();
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.e("MainActivity", "开始下拉刷新");
                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ptrFrameLayout.refreshComplete();
                                pageIndex++;
                                loadData();
                            }
                        });
                    }
                }.start();
            }
        });
    }

    @Override
    protected void initListener() {
    mlistView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        INewsModel iNewsModel = new NewsModelImp();
        iNewsModel.bloglist(pageIndex, this);
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void onSuccess(String xmlData) {
        XStream xStream = new XStream();
        xStream.alias("oschina", ItemBoke.class);
        xStream.alias("blog", ItemBoke.BlogBean.class);

        ItemBoke hotspot = (ItemBoke) xStream.fromXML(xmlData);
        Log.e("AAA", "数据解析成功" + hotspot.toString());
        mList.addAll(hotspot.getBlogs());
        bokelistViewAdapter = new BokeListViewAdapter(mList, getContext());
        mlistView.setAdapter(bokelistViewAdapter);
        bokelistViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errorMsg) {
        Log.e("AAA", "请求失败" + errorMsg.toString());

    }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String str = mList.get(position).getId();
            Intent intent = new Intent(getContext(), BokeXiangqing.class);
            intent.putExtra("id",str);
            startActivity(intent);

    }
}
