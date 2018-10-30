package com.example.lenovo.oschina.modle.http.biz;

import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

/**
 * Created by Lenovo on 2017/5/18.
 */

public interface LoginModel {


    //登录
    void Login(String username, String pwd,String keep_login, NetWorkCallBack callBack);

}
