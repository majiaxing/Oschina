package com.example.lenovo.oschina.modle.http.callback;

/**
 * Created by Lenovo on 2017/5/11.
 */

public interface NetWorkCallBack {


    /**
     * 网络请求成功的回调；
     * @param xmlData
     */
    void onSuccess(String xmlData);

    /**
     *网络请求失败的回调；
     * @param errorMsg
     */
    void onError(String errorMsg);

}
