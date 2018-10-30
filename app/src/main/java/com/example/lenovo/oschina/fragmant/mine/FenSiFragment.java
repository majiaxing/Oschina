package com.example.lenovo.oschina.fragmant.mine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.HomeActivity;
import com.example.lenovo.oschina.MainActivity;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.TableLayoutActivity;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.fragmant.FenSiAdapter;
import com.example.lenovo.oschina.modle.enitity.FenSiBean;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Lenovo on 2017/5/24.
 */
public class FenSiFragment extends BaseFragment {

    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    Unbinder unbinder;

    private INewsModel newsModel;
    private String Uid;
    private FenSiAdapter mAdapter;
    private List<FenSiBean.FriendBean> mList;
    @Override
    protected int layoutId() {
        return R.layout.pullrecycleview;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayout=new LinearLayoutManager(getActivity());
        PullToRecycleView.setLayoutManager(linearLayout);
        PullToRecycleView.addItemDecoration(new DividerItemDecoration(App.activity,DividerItemDecoration.VERTICAL));

    }

    @Override
    protected void initData() {

        newsModel = new NewsModelImp();
        SharedPreferences mShred=App.activity.getSharedPreferences("data",MODE_PRIVATE);
        String  uid=mShred.getString("uid","");
        this.Uid=uid;

        mList=new ArrayList<>();
        mAdapter=new FenSiAdapter(App.activity,mList);
        PullToRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        newsModel.Fensi(Uid, "0", new NetWorkCallBack() {
            @Override
            public void onSuccess(String result) {
                XStream xStream = new XStream();
                xStream.alias("oschina", FenSiBean.class);
                xStream.alias("friend", FenSiBean.FriendBean.class);
                FenSiBean bean = (FenSiBean) xStream.fromXML(result);
                List<FenSiBean.FriendBean> friends = bean.getFriends();
                Log.i("sd",friends.toString());
                mList.addAll(friends);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String MsgError) {

            }
        });
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }








}
