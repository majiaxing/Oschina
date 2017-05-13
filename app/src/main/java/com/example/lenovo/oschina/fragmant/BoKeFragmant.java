package com.example.lenovo.oschina.fragmant;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.coefig.ThreadUtils;
import com.example.lenovo.oschina.adapter.BokeListViewAdapter;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;
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

public class BoKeFragmant extends Fragment {
    private ListView mlistView;
    private List<ItemBoke.BlogBean> mList = new ArrayList<>();
    private BokeListViewAdapter bokelistViewAdapter;
    private PtrFrameLayout ptrFrameLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bokeviewpager_activity,null);
        mlistView = (ListView) view.findViewById(R.id.Boke_ListView);

        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.Boke_ptr);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(getContext());
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.setFooterView(footer);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.addPtrUIHandler(header);

        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                Log.e("MainActivity","开始加载更多");

                new Thread(){
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ptrFrameLayout.refreshComplete();
                                getVolley();
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.e("MainActivity","开始下拉刷新");
                new Thread(){
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ptrFrameLayout.refreshComplete();
                                getVolley();
                            }
                        });
                    }
                }.start();
            }
        });


        getVolley();

        return view;
    }

    private void getVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String url = "http://www.oschina.net/action/api/blog_list";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("AAA", "数据请求成功：" + s);

                XStream xStream = new XStream();
                xStream.alias("oschina",ItemBoke.class);
                xStream.alias("blog",ItemBoke.BlogBean.class);

                ItemBoke hotspot = (ItemBoke) xStream.fromXML(s);
                Log.e("AAA","数据解析成功"+hotspot.toString());
                mList.addAll(hotspot.getBlogs());
                bokelistViewAdapter = new BokeListViewAdapter(mList,getContext());
                mlistView.setAdapter(bokelistViewAdapter);
                bokelistViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("AAA", "请求失败：" + volleyError.getMessage().toString());
            }
        });
        requestQueue.add(stringRequest);
    }
}
