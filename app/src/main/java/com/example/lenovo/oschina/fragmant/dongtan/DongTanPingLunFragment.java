package com.example.lenovo.oschina.fragmant.dongtan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.dongtan.DongTanPingLunAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanPingLunBean;
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
 * Created by Lenovo on 2017/5/26.
 */
public class DongTanPingLunFragment extends BaseFragment {

    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    Unbinder unbinder;
    private INewsModel model;
    private List<DongTanPingLunBean.CommentBean> mList;
    private DongTanPingLunAdapter mAdapter;
    private SharedPreferences mShared;
    private String id;
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
    }

    @Override
    protected void initData() {
        model=new NewsModelImp();
        mList=new ArrayList<>();
        mAdapter=new DongTanPingLunAdapter(App.activity,mList);
        PullToRecycleView.setAdapter(mAdapter);
        mShared=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        id=mShared.getString("id","");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        model.DongTanPingLun(id, "0", new NetWorkCallBack() {
            @Override
            public void onSuccess(String result) {
                XStream xStream=new XStream();
                xStream.alias("oschina",DongTanPingLunBean.class);
                xStream.alias("comment",DongTanPingLunBean.CommentBean.class);
                DongTanPingLunBean  bean = (DongTanPingLunBean) xStream.fromXML(result);
                mList.addAll(bean.getComments());
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
