package com.example.lenovo.oschina.modle.http.biz;

import com.example.lenovo.oschina.coefig.Urls;
import com.example.lenovo.oschina.modle.http.HttpFactory;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.example.lenovo.oschina.util.OkhttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/4/22.
 */

public class DongTanImpl implements IDongTanModel {
    @Override
    public void faBiao(String uid, String msg, NetWorkCallBack callBack) {
        Map<String,String>  map=new HashMap<>();
        map.put("uid",uid);
        map.put("msg",msg);
        map.put("img","");
        map.put("amr","");
        OkhttpUtils.getInstance().Post(Urls.COMMENTPUB,map,callBack);
    }


}
