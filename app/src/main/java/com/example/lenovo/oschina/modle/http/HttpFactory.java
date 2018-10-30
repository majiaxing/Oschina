package com.example.lenovo.oschina.modle.http;

import com.example.lenovo.oschina.util.OkhttpUtils;
import com.example.lenovo.oschina.util.VolleyUtil;

/**
 * Created by Lenovo on 2017/5/11.
 */

public class HttpFactory {

    private static final int OKHTTP = 0;
    private static final int VOLLEY = 1;
    private static final int RETROFIT = 2;

    public static final int TYPE = VOLLEY;

    public static IHttp create(){
        IHttp iHttp = null;
        OkHttp okHttp = null;
        switch (TYPE){
            case VOLLEY:
                iHttp = VolleyUtil.getInstance();

                break;
            case OKHTTP:
                okHttp = OkhttpUtils.getInstance();
        }
        return iHttp;
    }

}
