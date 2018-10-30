package com.example.lenovo.oschina.fragmant.faxian;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.BoKeAdapter;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.Item;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/20.
 */
public class TuiJieFragment extends BaseActivity {
    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    private BoKeAdapter mAdapter;
    private INewsModel newsModel;
    private List<ItemBoke.BlogBean> newList;
    private int Index=1;






    @Override
    protected int getlayoutId() {
        return R.layout.tuijianpullrecycleview;
    }

    @Override
    protected void init() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        PullToRecycleView.addItemDecoration(new DividerItemDecoration(App.activity,DividerItemDecoration.VERTICAL));
        PullToRecycleView.setLayoutManager(layoutManager);
        PullToRecycleView.setPullRefreshEnabled(true);//下拉刷新
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
                        newList.clear();

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
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        newsModel=new NewsModelImp();
        newList=new ArrayList<>();
        mAdapter=new BoKeAdapter(this,newList);
        PullToRecycleView.setAdapter(mAdapter);

        newsModel.recommend(Index, new NetWorkCallBack() {
            @Override
            public void onSuccess(String result) {
                XStream xStream=new XStream();
                xStream.alias("oschina",ItemBoke.class);
                xStream.alias("blog",ItemBoke.BlogBean.class);
                ItemBoke boken = (ItemBoke) xStream.fromXML(result);
                newList.addAll(boken.getBlogs());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String MsgError) {

            }
        });
    }


}
