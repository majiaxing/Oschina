package com.example.lenovo.oschina.modle.http;

import android.widget.ImageView;

import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by admin on 2017/4/12.
 */

public interface OkHttp {
      void  post(String url, Map<String, String> params, NetWorkCallBack callBack);
      void  get(String url, Map<String, String> params, NetWorkCallBack callBack);
      void uploadFile();
      void download();
      void loadImage(String imgUrl, ImageView imageView);
      //获取cookie
      void Post(String url, Map<String, String> params, NetWorkCallBack callBack);
      void  Get(String url, Map<String, String> params, NetWorkCallBack callBack);
}
