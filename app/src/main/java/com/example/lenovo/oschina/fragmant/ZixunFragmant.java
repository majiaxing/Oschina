package com.example.lenovo.oschina.fragmant;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.WendaListViewAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.coefig.ThreadUtils;
import com.example.lenovo.oschina.adapter.ListViewAdapter;
import com.example.lenovo.oschina.adapter.TestNormalAdapter;
import com.example.lenovo.oschina.modle.enitity.Item;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;
import com.example.lenovo.oschina.modle.enitity.ItemWenda;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
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

public class ZixunFragmant extends BaseFragment implements NetWorkCallBack {
    private RollPagerView mRollViewPager;
    private ListView mlistView;
    private List<Item.NewsBean> mList = new ArrayList<>();
    private ListViewAdapter listViewAdapter;
    private PtrFrameLayout ptrFrameLayout;
    private int pageIndex = 0;
    private View view1,view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.zixunviewpager_activity, null);
        view1 = inflater.inflate(R.layout.lunbotu_activity, null);
        layoutId();
        initView(view);
        initData();
        loadData();
        getvolley();
        return view;
    }

    @Override
    protected int layoutId() {

        return R.layout.zixunviewpager_activity;
    }

    @Override
    protected void initView(View view) {
        mRollViewPager = (RollPagerView) view1.findViewById(R.id.Zixun_Lunbo);
        mlistView = (ListView) view.findViewById(R.id.Zixun_ListView);
        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.zixun_ptr);

    }

    @Override
    protected void initData() {

        listViewAdapter = new ListViewAdapter(mList,getContext());

        mlistView.addHeaderView(view1);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        mRollViewPager.setHintView(new ColorPointHintView(getContext(), Color.YELLOW, Color.WHITE));

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

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
                                getvolley();
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
                                pageIndex++;
                                ptrFrameLayout.refreshComplete();
                                getvolley();
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




    private void getvolley() {
        INewsModel iNewsModel = new NewsModelImp();
        iNewsModel.newsList(pageIndex,this);
    }


    @Override
    public void onSuccess(String xmlData) {
        Log.e("AAA", "请求成功" + xmlData.toString());


        XStream xStream = new XStream();
        xStream.alias("oschina",ItemBoke.class);
        xStream.alias("news",ItemBoke.BlogBean.class);
        Item hotspot = (Item) xStream.fromXML(xmlData);
        Log.e("AAA","数据解析成功"+hotspot.toString());
        mList.addAll(hotspot.getNewslist());

        mlistView.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();


    }

    @Override
    public void onError(String errorMsg) {
        Log.e("AAA", "请求失败" + errorMsg.toString());
    }
}
