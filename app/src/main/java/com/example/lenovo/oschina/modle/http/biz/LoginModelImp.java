package com.example.lenovo.oschina.modle.http.biz;

import com.example.lenovo.oschina.coefig.Urls;
import com.example.lenovo.oschina.modle.http.HttpFactory;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.example.lenovo.oschina.util.OkhttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/5/18.
 */

public class LoginModelImp implements LoginModel{

    /**
     *
     * 登录
     * */
    @Override
    public void Login(String username, String pwd,String keep_login, NetWorkCallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("pwd",pwd);
        map.put("keep_login",keep_login);
        OkhttpUtils.getInstance().Post(Urls.LOGIN, map, callBack);
    }
}
