package com.example.lenovo.oschina.fragmant.kaiyuanruanjian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.fenlei.MyAdapter;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.fenlei.FenLeiBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/5/25.
 */
public class ErjiFenleiFragment extends BaseFragment{

    private ListView mListView;
    private List<FenLeiBean.SoftwareTypeBean> mList = new ArrayList<>();
    private DiscoverModel discoverModel;
    private MyAdapter myAdapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected int layoutId() {
        return R.layout.fenlei_activity;
    }

    @Override
    protected void initView(View view) {
        mListView = (ListView)view.findViewById(R.id.ListView);
        myAdapter = new MyAdapter(mList,getContext());
        mListView.setAdapter(myAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = mList.get(position).getTag();
                SharedPreferences preferences=App.activity.getSharedPreferences("two",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("twotagagain",str);
                editor.commit();


                fragmentManager= App.activity.getSupportFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.Main_FrameLayout,new ErjiFenleiLeiBiaoFragment(),ErjiFenleiLeiBiaoFragment.class.getSimpleName());
                transaction.commit();


            }
        });
    }

    @Override
    protected void loadData() {
        SharedPreferences preferences= App.activity.getSharedPreferences("two", Context.MODE_PRIVATE);
        String twotag=preferences.getString("tag",null);

        discoverModel = new DiscoverModelImp();
        discoverModel.erjifenlei(twotag, new NetWorkCallBack() {
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
}
