package com.example.lenovo.oschina.fragmant.kaiyuanruanjian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.faxian.RuanjianXiangqing;
import com.example.lenovo.oschina.adapter.fenlei.MyAdapter;
import com.example.lenovo.oschina.adapter.fenlei.MyErjiAdapter;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.fenlei.ErjiFenleiBean;
import com.example.lenovo.oschina.modle.enitity.fenlei.FenLeiBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/5/25.
 */
public class ErjiFenleiLeiBiaoFragment extends BaseFragment{

    private ListView mListView;
    private List<ErjiFenleiBean.SoftwareBean> mList = new ArrayList<>();
    private MyErjiAdapter myErjiAdapter;
    private DiscoverModel discoverModel;
    private int Index = 1;
    @Override
    protected int layoutId() {
        return R.layout.fenlei_activity;
    }

    @Override
    protected void initView(View view) {
        mListView = (ListView)view.findViewById(R.id.ListView);
        myErjiAdapter = new MyErjiAdapter(mList,getContext());
        mListView.setAdapter(myErjiAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = mList.get(position).getUrl();
                Intent intent = new Intent(getContext(),RuanjianXiangqing.class);
                intent.putExtra("url",str);
                startActivity(intent);
        }
    });
    }

    @Override
    protected void loadData() {
        SharedPreferences preferences= App.activity.getSharedPreferences("two", Context.MODE_PRIVATE);
        String twotag=preferences.getString("tag",null);

        discoverModel = new DiscoverModelImp();
        discoverModel.erjifenleileibiao(twotag, Index, new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {
                XStream xStream=new XStream();
                xStream.alias("oschina",ErjiFenleiBean.class);
                xStream.alias("software",ErjiFenleiBean.SoftwareBean.class);
                ErjiFenleiBean tuiJieBean= (ErjiFenleiBean) xStream.fromXML(xmlData);
                mList.addAll(tuiJieBean.getSoftwares());
                myErjiAdapter.notifyDataSetChanged();
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
