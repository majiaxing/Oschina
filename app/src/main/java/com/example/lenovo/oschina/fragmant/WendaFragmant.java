package com.example.lenovo.oschina.fragmant;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.ZixunXiangqing;
import com.example.lenovo.oschina.activity.zixunxiangqing.Wendaxiangqing;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.coefig.ThreadUtils;
import com.example.lenovo.oschina.adapter.WendaListViewAdapter;
import com.example.lenovo.oschina.modle.enitity.Item;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;
import com.example.lenovo.oschina.modle.enitity.ItemWenda;
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

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by Lenovo on 2017/5/9.
 */

public class WendaFragmant extends BaseFragment implements NetWorkCallBack,AdapterView.OnItemClickListener{

    private ListView mlistView;
    private List<ItemWenda.PostBean> mList = new ArrayList<>();
    private WendaListViewAdapter wendaListViewAdapter;
    private PtrFrameLayout ptrFrameLayout;
    private  PtrClassicDefaultHeader header;
    private PtrClassicDefaultFooter footer;
    private int pageIndex = 0;

    @Override
    protected int layoutId() {
        return R.layout.wendaviewpager_activity;
    }

    @Override
    protected void initView(View view) {
        mlistView = (ListView) view.findViewById(R.id.Wend_ListView);

        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.Wenda_ptr);
    }

    @Override
    protected void initData() {
        header = new PtrClassicDefaultHeader(getContext());
        footer = new PtrClassicDefaultFooter(getContext());
        getVolley();
    }

    @Override
    protected void initListener() {
    mlistView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
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
                                pageIndex++;
                                getVolley();
                            }
                        });
                    }
                }.start();
            }
        });


    }

    @Override
    public void setParams(Bundle bundle) {

    }

    private void getVolley(){

        INewsModel iNewsModel = new NewsModelImp();
        iNewsModel.hotspot(pageIndex,this);
    }

    @Override
    public void onSuccess(String xmlData) {
//
        XStream xStream = new XStream();
        xStream.alias("oschina",ItemWenda.class);
        xStream.alias("post",ItemWenda.PostBean.class);
        ItemWenda hotspot = (ItemWenda) xStream.fromXML(xmlData);
        Log.e("AAA","数据解析成功"+hotspot.toString());

        mList.addAll(hotspot.getPosts());
        wendaListViewAdapter = new WendaListViewAdapter(mList,getContext());
        mlistView.setAdapter(wendaListViewAdapter);
        wendaListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errorMsg) {
        Log.e("AAA","请求失败"+errorMsg.toString());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String str = mList.get(position).getId();
        Intent intent = new Intent(getContext(), Wendaxiangqing.class);
        intent.putExtra("id",str);
        startActivity(intent);
    }
}
