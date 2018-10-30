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
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanBean;

import java.util.List;

/**
 * Created by admin on 2017/4/26.
 */

public class DongTanZanAdapter extends BaseAdapter<DongTanBean.TweetBean.UserBean> {
    public DongTanZanAdapter(Context context, List<DongTanBean.TweetBean.UserBean> datas) {
        super(context, R.layout.zan_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, DongTanBean.TweetBean.UserBean userBean) {
        holder.setText(R.id.Zan_Name,userBean.getName());
        final ImageView imageView = holder.getView(R.id.Zan_Image);
        Glide.with(context).load(userBean.getPortrait())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
