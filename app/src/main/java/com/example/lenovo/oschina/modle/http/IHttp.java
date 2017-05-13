package com.example.lenovo.oschina.modle.http;

import android.widget.ImageView;

import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Lenovo on 2017/5/11.
 */

public interface IHttp {

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    void post(String url, Map<String, String> params, NetWorkCallBack callBack);

    /**
     * 获取请求头的信息；
     * @param url
     * @param params
     * @param callBack
     */
    void coPost(String url, Map<String, String> params, NetWorkCallBack callBack);

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    void get(String url, Map<String, String> params, NetWorkCallBack callBack);

    /**
     * 需要
     * get请求
     * @param url
     * @param params
     * @param callBack
     */
    void coGet(String url,Map<String,String>params,NetWorkCallBack callBack);

    /**
     *
     */
    void iploadFile();

    /**
     *
     */
    void downlad();

    /**
     * 图片
     *
     * @param url
     * @param imageView
     */
    void loadImage(String url, ImageView imageView);


}
