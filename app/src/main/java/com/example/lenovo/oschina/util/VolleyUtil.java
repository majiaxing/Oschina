package com.example.lenovo.oschina.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.modle.http.IHttp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

import java.util.Map;
import java.util.Set;

/**
 * Created by Lenovo on 2017/5/11.
 */

public class VolleyUtil implements IHttp {

    private VolleyUtil() {
    }

    private static VolleyUtil volleyUtil = new VolleyUtil();

    public static VolleyUtil getInstance() {
        return volleyUtil;
    }


    @Override
    public void post(String url, Map<String, String> params, final NetWorkCallBack callBack) {
        RequestQueue requestQueue = Volley.newRequestQueue(App.activity);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("AAA", "数据请求成功：" + s);
                callBack.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("AAA", "请求失败：" + volleyError.getMessage().toString());
                callBack.onError(volleyError.getMessage());
            }
        });
        requestQueue.add(stringRequest);

    }

    @Override
    public void coPost(String url, Map<String, String> params, NetWorkCallBack callBack) {

    }

    @Override
    public void get( String url, final Map<String, String> params, final NetWorkCallBack callBack) {
        if (params != null && params.size() > 0) {
            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            Set<String> set = params.keySet();
            for (String s : set) {
                sb.append(s).append("=").append(params.get(s)).append("&");
            }
            url = sb.toString().substring(0, sb.length() - 1);
        }
            RequestQueue requestQueue = Volley.newRequestQueue(App.activity);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Log.e("AAA", "数据请求成功：" + s);
                    callBack.onSuccess(s);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("AAA", "请求失败：");
                    callBack.onError(volleyError.getMessage());
                }
            });
            requestQueue.add(stringRequest);
            Log.e("AAA", "拼接参数" + url );




    }

    @Override
    public void coGet(String url, Map<String, String> params, NetWorkCallBack callBack) {

    }

    @Override
    public void iploadFile() {

    }

    @Override
    public void downlad() {

    }

    @Override
    public void loadImage(String url, ImageView imageView) {

    }


}
