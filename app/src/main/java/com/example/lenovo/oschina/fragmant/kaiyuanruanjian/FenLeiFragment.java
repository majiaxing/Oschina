package com.example.lenovo.oschina.fragmant.kaiyuanruanjian;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.enitity.fenlei.FenLeiBean;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2017/4/15.
 */

public class FenLeiFragment extends BaseFragment {
    @BindView(R.id.PullToRecycleView)
    PullToRefreshRecyclerView PullToRecycleView;
    private List<FenLeiBean.SoftwareTypeBean> mList;


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
    }

    @Override
    protected void initData() {
//        mList=new ArrayList<>();
//        discoverModel=new DiscoverlImpl();
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


//    @Override
//    protected void loadData() {
//       discoverModel.fenleiLogin(new NetWorkCallBack() {
//           @Override
//           public void onSuccess(String result) {
//               XStream xStream=new XStream();
//               xStream.alias("oschina",FenLeiBean.class);
//               xStream.alias("softwareType",FenLeiBean.SoftwareTypeBean.class);
//             FenLeiBean  fenLeiBean= (FenLeiBean) xStream.fromXML(result);
//
//                 mList.addAll(fenLeiBean.getSoftwareTypes());
//               mAdapter=new FenLeiAdapter(getActivity().getApplicationContext(),mList);
//               PullToRecycleView.setAdapter(mAdapter);
//
//           }
//
//           @Override
//           public void onError(String MsgError) {
//
//           }
//       });
//    }
//
//    @Override
//    public void setParams(Bundle bundle) {
//
//    }
//
//    @Override
//    protected void updateTitleBar() {
////        super.updateTitleBar();
//        if (App.activity instanceof MainActivity) {
//            ((MainActivity) App.activity).getBottomGroup().setVisibility(View.GONE);
//            ((MainActivity) App.activity).getTitleText().setText("开源软件");
//
//        }
//
//    }
}
