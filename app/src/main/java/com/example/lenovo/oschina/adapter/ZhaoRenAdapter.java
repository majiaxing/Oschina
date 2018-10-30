package com.example.lenovo.oschina.adapter;

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
import com.example.lenovo.oschina.modle.enitity.ZhaoRenBean;

import java.util.List;

/**
 * Created by admin on 2017/4/20.
 */

public class ZhaoRenAdapter extends BaseAdapter<ZhaoRenBean.UserBean> {
    public ZhaoRenAdapter(Context context, List<ZhaoRenBean.UserBean> datas) {
        super(context, R.layout.zhaoren_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, ZhaoRenBean.UserBean userBean) {
     holder.setText(R.id.ZhaoType,userBean.getFrom());
        holder.setText(R.id.ZhaoName,userBean.getName());
        holder.setText(R.id.ZhaoCity,userBean.getGender());

        final ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.ZhaoImage);
//        Glide.with(context).load(blogBean).into(imageView);
        Glide.with(context).load(userBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                ciDrawable.setCircular(true);
                imageView.setImageDrawable(ciDrawable);
            }
        });
    }
}
