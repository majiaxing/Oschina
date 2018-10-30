package com.example.lenovo.oschina.modle.http.biz;

import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

/**
 * Created by Lenovo on 2017/5/15.
 */

public interface DiscoverModel {

    //最新动弹
    void tweet(int pageIndex, NetWorkCallBack callBack);

    //热门动弹
    void Rementweet(int pageIndex, NetWorkCallBack callBack);

    //点赞喜欢
    void DianZanXiHuan(String tweetid,String  uid,String ownerOfTweet,NetWorkCallBack callBack);
    //点赞不喜欢
    void DianZanBuXiHua(String tweetid,String  uid,String ownerOfTweet,NetWorkCallBack callBack);
    //线下 活动
    void  XianXiaHuoDong(String  pageIndex,String uid,String pageSize,NetWorkCallBack callBack);
    //线下活动详情
    void XianXiaXiangQing(String id,NetWorkCallBack callBack);

    //我的动弹
    void DongTanMyLogin(int PageIndex,NetWorkCallBack callBack);
    //关注
    void GuanZhu(String uid, String pageIndex, NetWorkCallBack callBack);
    //开源软件

    //分类
    void  fenleiLogin(NetWorkCallBack callBack);

    //二级分类
    void erjifenlei(String tag,NetWorkCallBack callBack);

    //二级分类列表
    void erjifenleileibiao(String searchTag,int pageIndex,NetWorkCallBack callBack);

    //推荐
    void  tuijie(String pageIndex,NetWorkCallBack callBack);
    //最新
    void  zuixin(String pageIndex,NetWorkCallBack callBack);
    //热门
    void remen(String pageIndex,NetWorkCallBack callBack);
    //国产
    void  guochan(String pageIndex,NetWorkCallBack callBack);

}
