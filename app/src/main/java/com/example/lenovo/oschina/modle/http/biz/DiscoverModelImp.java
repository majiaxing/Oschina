package com.example.lenovo.oschina.modle.http.biz;

import com.example.lenovo.oschina.coefig.Keys;
import com.example.lenovo.oschina.coefig.Urls;
import com.example.lenovo.oschina.modle.http.HttpFactory;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.tag;

/**
 * Created by Lenovo on 2017/5/15.
 */

public class    DiscoverModelImp implements DiscoverModel{


    /**
     *
     *最新动弹
     *
     * */
    @Override
    public void tweet(int pageIndex, NetWorkCallBack callBack) {

        Map<String, String> commend = new HashMap<>();
        commend.put("uid", Keys.PAGEINDEX);
        commend.put("pageIndex", String.valueOf(pageIndex));
        commend.put("pageSize", Keys.PAGESIZE);

        HttpFactory.create().get(Urls.TWEET_LIST, commend, callBack);

    }

    @Override
    public void Rementweet(int pageIndex, NetWorkCallBack callBack) {

        Map<String, String> commend = new HashMap<>();
        commend.put("uid", Keys.UID);
        commend.put("pageIndex", String.valueOf(pageIndex));
        commend.put("pageSize", Keys.PAGESIZE);

        HttpFactory.create().get(Urls.TWEET_LIST, commend, callBack);
    }

    /**
     * 点赞喜欢
     *
     * */
    @Override
    public void DianZanXiHuan(String tweetid, String uid, String ownerOfTweet, NetWorkCallBack callBack) {
        Map<String,String>  map=new HashMap<>();
        map.put("tweetid",tweetid);
        map.put("uid",uid);
        map.put("ownerOfTweet",ownerOfTweet);
        HttpFactory.create().get(Urls.DianZanXiHuan,map,callBack);
    }
/**
 * 点赞不喜欢
 *
 * */
    @Override
    public void DianZanBuXiHua(String tweetid, String uid, String ownerOfTweet, NetWorkCallBack callBack) {

        Map<String,String>  map=new HashMap<>();
        map.put("tweetid",tweetid);
        map.put("uid",uid);
        map.put("ownerOfTweet",ownerOfTweet);
        HttpFactory.create().get(Urls.DianZanBuXiHuan,map,callBack);
    }

    /**
     * 线下活动
     * */

    @Override
    public void XianXiaHuoDong(String pageIndex, String uid, String pageSize, NetWorkCallBack callBack) {
        Map<String,String>  map=new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("uid",uid);
        map.put("pageSize",pageSize);
        HttpFactory.create().get(Urls.LINEAC,map,callBack);


    }
    /**
     * 线下活动详情
     * */

    @Override
    public void XianXiaXiangQing(String id, NetWorkCallBack callBack) {
        Map<String,String>  map=new HashMap<>();
        map.put("id",id);
        HttpFactory.create().get(Urls.LINEID,map,callBack);
    }

    /**
     * 我的动弹
     *
     * */

    @Override
    public void DongTanMyLogin(int PageIndex, NetWorkCallBack callBack) {

        Map<String, String> commend = new HashMap<>();
        commend.put("uid", Keys.PAGEINDEX);
        commend.put("pageIndex", String.valueOf(PageIndex));
        commend.put("pageSize", Keys.PAGESIZE);

        HttpFactory.create().get(Urls.TWEET_LIST, commend, callBack);

    }


    /**
     * 关注
     * */

    @Override
    public void GuanZhu(String uid, String pageIndex, NetWorkCallBack callBack) {
        Map<String,String>  map=new HashMap<>();
        map.put("uid",uid);
        map.put("relation","1");
        map.put("pageIndex",pageIndex);
        map.put("pageSize","20");
        HttpFactory.create().get(Urls.FenSi,map,callBack);
    }





    /**
     * 开源软件
     *
     * 分类
     *
     * */
    @Override
    public void fenleiLogin(NetWorkCallBack callBack) {
        Map<String,String>   map=new HashMap<>();
        map.put("type","0");
        HttpFactory.create().get(Urls.FenLei,map,callBack);
    }



    /**
     * 二级分类
     * */
    @Override
    public void erjifenlei(String tag,NetWorkCallBack callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("tag",tag);
        HttpFactory.create().get(Urls.FenLei_ErJi,map,callBack);
    }

    /**
     * 二级分类列表
     * */
    @Override
    public void erjifenleileibiao(String searchTag, int pageIndex, NetWorkCallBack callBack) {
        Map<String,String>  map=new HashMap<>();
        map.put("searchTag",searchTag);
        map.put("pageIndex", String.valueOf(pageIndex));
        map.put("pageSize","20");
        HttpFactory.create().get(Urls.FINDSOFTWARETAG,map,callBack);



    }

    /**
     * 推荐
     *
     * */
    @Override
    public void tuijie(String pageIndex, NetWorkCallBack callBack) {
        Map<String,String>   map=new HashMap<>();
        map.put("searchTag","recommend");
        map.put("pageIndex",pageIndex);
        map.put("pageSize","20");
        HttpFactory.create().get(Urls.TuiJie,map,callBack);
    }


    /**
     *
     * 资讯
     *
     * */

    @Override
    public void zuixin(String pageIndex, NetWorkCallBack callBack) {
        Map<String,String>   map=new HashMap<>();
        map.put("searchTag","time");
        map.put("pageIndex",pageIndex);
        map.put("pageSize","20");
        HttpFactory.create().get(Urls.ZuiXin_,map,callBack);
    }
    /**
     * 热门
     *
     * */
    @Override
    public void remen(String pageIndex, NetWorkCallBack callBack) {
        Map<String,String>   map=new HashMap<>();
        map.put("searchTag","view");
        map.put("pageIndex",pageIndex);
        map.put("pageSize","20");
        HttpFactory.create().get(Urls.Remen_,map,callBack);
    }

    /**
     * 国产
     *
     * */
    @Override
    public void guochan(String pageIndex, NetWorkCallBack callBack) {
        Map<String,String>   map=new HashMap<>();
        map.put("searchTag","list_cn");
        map.put("pageIndex",pageIndex);
        map.put("pageSize","20");
        HttpFactory.create().get(Urls.GuoChan,map,callBack);
    }


}
