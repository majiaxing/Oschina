package com.example.lenovo.oschina.fragmant.kaiyuanruanjian;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.kaiyuanruanjian.GuochanAdapter;
import com.example.lenovo.oschina.adapter.kaiyuanruanjian.TuijianAdapter;
import com.example.lenovo.oschina.adapter.kaiyuanruanjian.ZuiXinAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.kaiyuanruanjian.TuiJieBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2017/4/16.
 */

public class GuoChanFragment extends BaseFragment {
    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    private List<TuiJieBean.SoftwareBean> mList;
    private TuijianAdapter guoChanAdapter;
    private DiscoverModel discoverModel;
    private int Index=1;

    @Override
    protected int layoutId() {
        return R.layout.pullrecycleview;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        PullToRecycleView.addItemDecoration(new DividerItemDecoration(App.activity,DividerItemDecoration.VERTICAL));
        PullToRecycleView.setLayoutManager(layoutManager);
        PullToRecycleView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                PullToRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PullToRecycleView.setRefreshComplete();
                        mList.clear();

                        loadData();
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
        mList=new ArrayList<>();
        discoverModel=new DiscoverModelImp();
        guoChanAdapter=new TuijianAdapter(getActivity().getApplication(),mList);
        PullToRecycleView.setAdapter(guoChanAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        discoverModel.guochan(String.valueOf(Index), new NetWorkCallBack() {
            @Override
            public void onSuccess(String result) {
                XStream xStream=new XStream();
                xStream.alias("oschina",TuiJieBean.class);
                xStream.alias("software",TuiJieBean.SoftwareBean.class);
                TuiJieBean tuiJieBean= (TuiJieBean) xStream.fromXML(result);
                mList.addAll(tuiJieBean.getSoftwares());
                guoChanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String MsgError) {

            }
        });
    }

    @Override
    public void setParams(Bundle bundle) {

    }
}
