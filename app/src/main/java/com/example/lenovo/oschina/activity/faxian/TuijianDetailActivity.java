package com.example.lenovo.oschina.activity.faxian;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.modle.enitity.kaiyuanruanjian.RuanjianDetailBean;
import com.example.lenovo.oschina.modle.enitity.xiangqing.ZixunxiangqingBean;
import com.example.lenovo.oschina.modle.http.biz.INewsModel;
import com.example.lenovo.oschina.modle.http.biz.NewsModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2017/5/24.
 */
public class TuijianDetailActivity extends BaseActivity {


    @BindView(R.id.Fanhui)
    ImageView Fanhui;
    @BindView(R.id.Xiangqing_WebView)
    WebView mWebView;
    private String rurl;


    @Override
    protected int getlayoutId() {
        return R.layout.kaiyuanzixun_detail;
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
        String id = intent.getStringExtra("id");

        INewsModel iNewsModel = new NewsModelImp();
        iNewsModel.RuanjianDetail(id, new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {
                XStream xStream = new XStream();
                xStream.alias("oschina",RuanjianDetailBean.class);
                xStream.alias("relative", RuanjianDetailBean.SoftwareBean.class);
                RuanjianDetailBean hotspot = (RuanjianDetailBean) xStream.fromXML(xmlData);
                Log.e("AAA","数据解析成功"+hotspot.toString());

                rurl = hotspot.getSoftware().getUrl();
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
        });
    }



    @OnClick(R.id.Fanhui)
    public void onViewClicked() {
        finish();
    }
}
