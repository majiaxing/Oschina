package com.example.lenovo.oschina.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.DongTanDetailActivity;
import com.example.lenovo.oschina.coefig.DataTimeUitls;
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanBean;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModel;
import com.example.lenovo.oschina.modle.http.biz.DiscoverModelImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/5/15.
 */

public class DongtanZuixinAdapter extends BaseAdapter<DongTanBean.TweetBean> {

    private Context context;
    private DiscoverModel discoverModel;
    private ImageView Image;
    private String islike;

    public DongtanZuixinAdapter(Context context, List<DongTanBean.TweetBean> datas) {
        super(context, R.layout.tweet_item, datas);
        this.context = context;
        discoverModel=new DiscoverModelImp();
    }



    @Override
    public void convert(ViewHolder holder, final DongTanBean.TweetBean tweetBean) {

        Image= (ImageView) holder.itemView.findViewById(R.id.DongTan_zanImage);
        if ("1".equals(tweetBean.getIsLike())){
            Image.setImageResource(R.drawable.ic_thumbup_actived);
        }else {

            Image.setImageResource(R.drawable.ic_thumb_normal);
        }

        ImageView  image= (ImageView) holder.itemView.findViewById(R.id.DongTan_ImageView);
        if(tweetBean.getImgBig().equals("")){
            image.setVisibility(View.GONE);
        }else {
            Glide.with(context).load(tweetBean.getImgBig()).into(image);
            image.setVisibility(View.VISIBLE);
        }
        final ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.DongTan_head);
//        Glide.with(context).load(blogBean).into(imageView);
        Glide.with(context).load(tweetBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                ciDrawable.setCircular(true);
                imageView.setImageDrawable(ciDrawable);
            }
        });

        holder.setText(R.id.DongTan_pinlun,tweetBean.getCommentCount());
        holder.setText(R.id.DongTan_name, tweetBean.getAuthor());
        holder.setText(R.id.DongTan_body, tweetBean.getBody());
        if ("0".equals(tweetBean.getIsLike())){
            holder.setText(R.id.DongTan_zan,"赞");
        }

        holder.setText(R.id.DongTan_zan,tweetBean.getLikeCount());
        switch (tweetBean.getAppclient()){
            case "3":
                holder.setText(R.id.DongTan_phone,"Android");
                break;
            case "4":
                holder.setText(R.id.DongTan_phone,"ipone");
                break;
            case  "1":
                holder.setText(R.id.DongTan_phone,"SamSung");
                break;
        }
        //将时间转换
        String date= DataTimeUitls.getData(tweetBean.getPubDate());
        holder.setText(R.id.DongTan_date,date);

        //跳转到动弹详情
        holder.setOnclickListener(R.id.dongtan_lin, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(context, DongTanDetailActivity.class);
                List<DongTanBean.TweetBean.UserBean> list=tweetBean.getLikeList();
                intent.putParcelableArrayListExtra("zanList",(ArrayList<? extends Parcelable>) list);
                intent.putExtra("por",tweetBean.getPortrait());
                intent.putExtra("name",tweetBean.getAuthor());
                intent.putExtra("body",tweetBean.getBody());
                intent.putExtra("image",tweetBean.getImgBig());
                intent.putExtra("time",tweetBean.getPubDate());
                intent.putExtra("phone",tweetBean.getAppclient());
                intent.putExtra("likecount",tweetBean.getLikeCount());

                App.activity.startActivity(intent);
            }
        });


    }

//    private  void quXiaoZan(final ZuixinBean.TweetBean blogBean,final ViewHolder holder,final boolean[] boo){
//        discoverModel.DianZanBuXiHua(blogBean.getId(),  blogBean.getAuthor(), new NetWorkCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                Image=holder.getView(R.id.DongTan_zanImage);
//                Image.setImageResource(R.drawable.ic_thumb_normal);
//                boo[0]=true;
//                holder.setText(R.id.DongTan_zan,blogBean.getLikeCount());
//            }
//
//            @Override
//            public void onError(String MsgError) {
//
//            }
//        });
//    }


}
