package com.example.lenovo.oschina.fragmant.kaiyuanruanjian;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.fenlei.MyAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.fenlei.FenLeiBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/4/19.
 */

public class FenLeiZongFragment extends BaseFragment {

    @BindView(R.id.ListView)
    android.widget.ListView ListView;
    Unbinder unbinder;
    private List<FenLeiBean.SoftwareTypeBean> mList = new ArrayList<>();
    private MyAdapter myAdapter;
    private DiscoverModel discoverModel;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Override
    protected int layoutId() {
        return R.layout.fenlei_activity;
    }


    @Override
    protected void initData() {
        discoverModel=new DiscoverModelImp();
        myAdapter=new MyAdapter(mList,getContext());
        ListView.setAdapter(myAdapter);
    }

    @Override
    protected void initView(View view) {
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = mList.get(position).getTag();
//                SharedPreferences preferences= App.activity.getSharedPreferences("two", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor=preferences.edit();
//                editor.putString("tag",str);
//                editor.commit();

                fragmentManager = App.activity.getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.Main_FrameLayout , new ErjiFenleiFragment() , ErjiFenleiFragment.class.getSimpleName());
                transaction.commit();

            }
        });

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void loadData() {

        discoverModel.fenleiLogin(new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {

                XStream xStream=new XStream();
                xStream.alias("oschina",FenLeiBean.class);
                xStream.alias("softwareType",FenLeiBean.SoftwareTypeBean.class);
                FenLeiBean tuiJieBean= (FenLeiBean) xStream.fromXML(xmlData);
                mList.addAll(tuiJieBean.getSoftwareTypes());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMsg) {

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
