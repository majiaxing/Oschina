package com.example.lenovo.oschina.fragmant.dongtan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.dongtan.DongTanZanAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by admin on 2017/4/25.
 */

public class DongTanZanFragment extends BaseFragment {
//    private List<DongTanBean.TweetBean.UserBean>  mList=new ArrayList<>();
    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    private DongTanZanAdapter fragmentAdapter;
    private DiscoverModel newsModel;
    private SharedPreferences mShared;
    private ArrayList<DongTanBean.TweetBean.UserBean> list;
    private int Index=0;

    public ArrayList<DongTanBean.TweetBean.UserBean>  getList(){
        fragmentAdapter.notifyDataSetChanged();
        return  list;
    }
    public  void setList(ArrayList<DongTanBean.TweetBean.UserBean> list){

        this.list=list;
    }



    @Override
    protected int layoutId() {
        return R.layout.pullrecycleview;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        PullToRecycleView.addItemDecoration(new DividerItemDecoration(App.activity,DividerItemDecoration.VERTICAL));
        PullToRecycleView.setLayoutManager(layoutManager);
        fragmentAdapter=new DongTanZanAdapter(getContext(),list);
        PullToRecycleView.setAdapter(fragmentAdapter);
        getList();
        fragmentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

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
}
