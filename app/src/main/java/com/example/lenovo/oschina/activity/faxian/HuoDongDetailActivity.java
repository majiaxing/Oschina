package com.example.lenovo.oschina.activity.faxian;

import android.content.Intent;
import android.webkit.WebViewClient;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.modle.enitity.HuoDongDetailBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;

/**
 * Created by Lenovo on 2017/5/20.
 */
public class HuoDongDetailActivity extends BaseActivity {

    @BindView(R.id.WebView)
    android.webkit.WebView WebView;
    private String id;
    private Intent mIntent;
    private DiscoverModel model;

    @Override
    protected int getlayoutId() {
        return R.layout.activity_huo_dong_detail;
    }

    @Override
    protected void init() {
        mIntent=getIntent();
        id=mIntent.getStringExtra("id");
        model=new DiscoverModelImp();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        model.XianXiaXiangQing(id, new NetWorkCallBack() {
            @Override
            public void onSuccess(String result) {
                XStream xStream=new XStream();
                xStream.alias("oschina", HuoDongDetailBean.class);
                xStream.alias("post",HuoDongDetailBean.PostBean.class);
                HuoDongDetailBean bean= (HuoDongDetailBean) xStream.fromXML(result);
                String url=bean.getPost().getUrl();
                WebView.loadUrl(url);
                WebView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(String MsgError) {

            }
        });
    }
}
