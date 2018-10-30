package com.example.lenovo.oschina.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.HomeActivity;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.modle.enitity.xiangqing.ZixunxiangqingBean;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

/**
 * Created by Lenovo on 2017/5/17.
 */

public class ZixunXiangqing extends BaseActivity implements NetWorkCallBack,View.OnClickListener{


    private WebView mWebView;

    private String rurl;

    private ImageView mImage;
    @Override
    protected int getlayoutId() {
        return R.layout.zixunxiangqing_activity;
    }

    @Override
    protected void init() {

    mWebView = (WebView) findViewById(R.id.Xiangqing_WebView);
        mImage = (ImageView) findViewById(R.id.Fanhui);
    }

    @Override
    protected void initListener() {
    mImage.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        INewsModel iNewsModel = new NewsModelImp();
        iNewsModel.getNewsDetail(id,this);



    }


    @Override
    public void onSuccess(String xmlData) {

        Log.e("AAA", "请求成功" + xmlData.toString());


        XStream xStream = new XStream();
        xStream.alias("oschina",ZixunxiangqingBean.class);
        xStream.alias("relative", ZixunxiangqingBean.NewsBean.RelativeBean.class);
        ZixunxiangqingBean hotspot = (ZixunxiangqingBean) xStream.fromXML(xmlData);
        Log.e("AAA","数据解析成功"+hotspot.toString());

        rurl = hotspot.getNews().getUrl();
        Log.e("vvv","Url"+rurl);


        mWebView.loadUrl(rurl);
      //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
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
    public void onError(String errorMsg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Fanhui:
             onBackPressed();
                break;
        }
    }
}
