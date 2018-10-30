package com.example.lenovo.oschina.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.modle.http.OkHttp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/4/12.
 */

public class OkhttpUtils implements OkHttp {
    private OkHttpClient okHttpClient;

    private OkhttpUtils(){}
    //创建请求对象
    private static OkhttpUtils okHttpUtils=new OkhttpUtils();

    public static  OkhttpUtils getInstance(){
        return  okHttpUtils;
    }

    private OkHttpClient  okhttpClient=new OkHttpClient.Builder().build();
    @Override
    public void post(String url, Map<String, String> params, final NetWorkCallBack callBack) {

        FormBody.Builder  builder=new FormBody.Builder();
        if(params!=null){
            Set<String> keySet = params.keySet();
            for(String key:keySet){
                String value=params.get(key);
                builder.add(key,value);
            }
        }
        final Request  request=new Request.Builder().post(builder.build()).url(url).build();
        okhttpClient.newCall(request) .enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e.getMessage());
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str=response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(str);
                    }
                });
            }
        });
    }

    @Override
    public void get(String url, Map<String, String> params, final NetWorkCallBack callBack) {
                if (params!=null&&params.size()>0){
                    StringBuffer sb=new StringBuffer(url);
                    sb.append("?");
                    Set<String> set = params.keySet();
                    for (String s : set) {
                        sb.append(s).append("=").append(params.get(s)).append("&");
                    }
                    url=sb.toString().substring(0,sb.length()-1);
                }
        final Request  requset=new Request.Builder().url(url).build();
        okhttpClient.newCall(requset).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage());
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                      final String result=response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(result);
                    }
                });
            }
        });
    }
    @Override
    public void uploadFile() {
    }
    @Override
    public void download() {
    }
    @Override
    public void loadImage(String imgUrl, ImageView imageView) {
        Glide.with(App.activity).load(imgUrl).into(imageView);
    }

    @Override
    public void Post(String url, Map<String, String> params, final NetWorkCallBack callBack) {
        FormBody.Builder  builder=new FormBody.Builder();
        if(params!=null){
            Set<String> keySet = params.keySet();
            for(String key:keySet){
                String value=params.get(key);
                builder.add(key,value);
            }
        }
        final Request  request=new Request.Builder()
                .post(builder.build())
                .addHeader("Cookie",getCookie())
                .url(url)
                .build();
        okhttpClient.newCall(request) .enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

               callBack.onError(e.getMessage());
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str=response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(str);
                    }
                });
            }
        });

    }

    @Override
    public void Get(String url, Map<String, String> params, final NetWorkCallBack callBack) {
        if (params!=null&&params.size()>0){
            StringBuffer sb=new StringBuffer(url);
            sb.append("?");
            Set<String> set = params.keySet();
            for (String s : set) {
                sb.append(s).append("=").append(params.get(s)).append("&");
            }
            url=sb.toString().substring(0,sb.length()-1);
        }
        final Request  requset=new Request.Builder().addHeader("Cookie",getCookie()).url(url).build();
        okhttpClient.newCall(requset).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage());
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result=response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(result);
                    }
                });
            }
        });
    }

    private String getCookie(){
        String cookie="";
        SharedPreferences  mShared=App.activity.getSharedPreferences("data",Context.MODE_PRIVATE);
        cookie=mShared.getString("cookie","");
        Log.e("AAA","cookie"+cookie);
        return  cookie;


    }
}
