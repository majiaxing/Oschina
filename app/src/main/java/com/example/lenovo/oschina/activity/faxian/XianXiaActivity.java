package com.example.lenovo.oschina.activity.faxian;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.TestNormalAdapter;
import com.example.lenovo.oschina.adapter.XIanXiaHuoDongAdapter;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.modle.enitity.XianXiaHuoDongBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.jude.rollviewpager.RollPagerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by Lenovo on 2017/5/20.
 */
public class XianXiaActivity extends BaseActivity{

    @BindView(R.id.Back)
    ImageView Back;
    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    private DiscoverModel model;
    private XIanXiaHuoDongAdapter mAdapter;
    private List<XianXiaHuoDongBean.EventBean> mList;
    private SharedPreferences mShared;
    private String Uid;
    private RollPagerView mRollViewPager;
    private int Index=1;

    @Override
    protected int getlayoutId() {
        return R.layout.activity_xian_xia_detail;
    }

    @Override
    protected void init() {
        LinearLayoutManager linearLayout=new LinearLayoutManager(getApplicationContext());
        PullToRecycleView.addItemDecoration(new DividerItemDecoration(App.activity,DividerItemDecoration.VERTICAL));
        PullToRecycleView.setLayoutManager(linearLayout);
        PullToRecycleView.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        PullToRecycleView.setLoadingMoreEnabled(true);
//      开启刷新回调
        PullToRecycleView.displayLastRefreshTime(true);
        PullToRecycleView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                PullToRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PullToRecycleView.setRefreshComplete();
                        mList.clear();
                        Index++;
                        loadData();
                    }
                }, 2000);
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
                }, 2000);
            }
        });


        View view1= LayoutInflater.from(this).inflate(R.layout.view,null);
        mRollViewPager= (RollPagerView) view1.findViewById(R.id.roll_view_pager);
        PullToRecycleView.addHeaderView(view1);
//        设置播放时间间隔
        mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());
        mShared= App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        String uid=mShared.getString("uid","");
        this.Uid=uid;


        model=new DiscoverModelImp();
        mList=new ArrayList<>();
        mAdapter=new XIanXiaHuoDongAdapter(this,mList);
        PullToRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
    model.XianXiaHuoDong("1", "0", "15", new NetWorkCallBack() {
        @Override
        public void onSuccess(String xmlData) {
            XStream xStream=new XStream();
            xStream.alias("oschina",XianXiaHuoDongBean.class);
            xStream.alias("event",XianXiaHuoDongBean.EventBean.class);
            XianXiaHuoDongBean xiaHuoDongBean = (XianXiaHuoDongBean) xStream.fromXML(xmlData);
            Log.i("sdd",xiaHuoDongBean.getEvents().toString());
            mList.addAll(xiaHuoDongBean.getEvents());
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(String errorMsg) {

        }
    });

}
}