package com.example.lenovo.oschina.fragmant.dongtan;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.DongtanZuixinAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Lenovo on 2017/5/15.
 */

public class RemenFragment extends BaseFragment implements NetWorkCallBack{


    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView recycleView;

    private DongtanZuixinAdapter mAdapter;
    private DiscoverModel discoverModel;
    private List<DongTanBean.TweetBean> mList;
    private int Index = 0;


    @Override
    protected int layoutId() {
        return R.layout.zuixin_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recycleView.setLayoutManager(layoutManager);
        recycleView.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        recycleView.setLoadingMoreEnabled(true);
        //开启刷新回调
        recycleView.displayLastRefreshTime(true);
        //停止刷新
        //停止刷新
        recycleView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                recycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycleView.setRefreshComplete();
                        mList.clear();
                        loadData();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                recycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycleView.setLoadMoreComplete();
                        Index++;
                        loadData();

                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void initData() {
        discoverModel = new DiscoverModelImp();
        mList = new ArrayList<>();
        mAdapter = new DongtanZuixinAdapter(getActivity().getApplication(), mList);
        recycleView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        discoverModel.Rementweet(Index, this);
    }

    @Override
    public void setParams(Bundle bundle) {

    }


    @Override
    public void onSuccess(String xmlData) {
        XStream xStream = new XStream();
        xStream.alias("oschina", DongTanBean.class);
        xStream.alias("tweet", DongTanBean.TweetBean.class);
        xStream.alias("user",DongTanBean.TweetBean.UserBean.class);

        DongTanBean bean = (DongTanBean) xStream.fromXML(xmlData);
        mList.addAll(bean.getTweets());
//        Log.e("AAA","解析成功"+bean.getTweets());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errorMsg) {

    }
}
