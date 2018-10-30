package com.example.lenovo.oschina.activity.faxian;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2017/5/25.
 */
public class RuanjianXiangqing extends BaseActivity {


    @BindView(R.id.Fanhui)
    ImageView Fanhui;
    @BindView(R.id.Xiangqing_WebView)
    WebView XiangqingWebView;

    @Override
    protected int getlayoutId() {
        return R.layout.ruanjianxiangqing_activity;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        XiangqingWebView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        XiangqingWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Fanhui)
    public void onViewClicked() {
    }
}
