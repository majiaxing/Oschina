package com.example.lenovo.oschina.adapter.dongtan;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.coefig.DataTimeUitls;
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanPingLunBean;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/26.
 */
public class DongTanPingLunAdapter extends BaseAdapter<DongTanPingLunBean.CommentBean> {
    public DongTanPingLunAdapter(Context context, List<DongTanPingLunBean.CommentBean> datas) {
        super(context, R.layout.pinglun_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, DongTanPingLunBean.CommentBean commentBean) {
        final ImageView imageView=holder.getView(R.id.pinglunlist_head);
        Glide.with(context).load(commentBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                ciDrawable.setCircular(true);
                imageView.setImageDrawable(ciDrawable);
            }
        });
        holder.setText(R.id.pinglun_Name,commentBean.getAuthor());
        String date= DataTimeUitls.getData(commentBean.getPubDate());
        holder.setText(R.id.pinglun_shijian,date);
        holder.setText(R.id.pinglun_content,commentBean.getContent());
    }
    }

