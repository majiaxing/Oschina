package com.example.lenovo.oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.faxian.HuoDongDetailActivity;
import com.example.lenovo.oschina.modle.enitity.XianXiaHuoDongBean;

import java.util.List;

/**
 * Created by admin on 2017/4/23.
 */

public class XIanXiaHuoDongAdapter extends BaseAdapter<XianXiaHuoDongBean.EventBean> {

    public XIanXiaHuoDongAdapter(Context context, List<XianXiaHuoDongBean.EventBean> datas) {
        super(context, R.layout.xianxiahuodong_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final XianXiaHuoDongBean.EventBean eventBean) {
        holder.setText(R.id.XianXia_Title,eventBean.getTitle());
        holder.setText(R.id.XianXia_Time,eventBean.getStartTime());
        ImageView  imageView= (ImageView) holder.itemView.findViewById(R.id.XianXia_Image);
        Glide.with(context).load(eventBean.getCover()).into(imageView);
        holder.setOnclickListener(R.id.ItEm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(context, HuoDongDetailActivity.class);
                intent.putExtra("id",eventBean.getId());
                App.activity.startActivity(intent);
            }
        });
    }
}
