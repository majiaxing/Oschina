package com.example.lenovo.oschina.fragmant;

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
import com.example.lenovo.oschina.modle.enitity.FenSiBean;
import com.example.lenovo.oschina.modle.enitity.kaiyuanruanjian.FenLeiBean;

import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */

public class FenSiAdapter extends BaseAdapter<FenSiBean.FriendBean>{
    public FenSiAdapter(Context context, List<FenSiBean.FriendBean> datas) {
        super(context, R.layout.fensi_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, FenSiBean.FriendBean noticeBean) {
          holder.setText(R.id.FenSi_Name,noticeBean.getName());
          holder.setText(R.id.FenSi_QianMing,noticeBean.getFrom());
      final   ImageView  imageView=holder.getView(R.id.FenSi_Image);
        Glide.with(context).load(noticeBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                ciDrawable.setCircular(true);
                imageView.setImageDrawable(ciDrawable);
            }
        });

    }
}
