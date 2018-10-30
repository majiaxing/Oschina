package com.example.lenovo.oschina.adapter.kaiyuanruanjian;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.DongTanDetailActivity;
import com.example.lenovo.oschina.activity.faxian.TuijianDetailActivity;
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanBean;
import com.example.lenovo.oschina.modle.enitity.kaiyuanruanjian.TuiJieBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/5/24.
 */

public class TuijianAdapter extends BaseAdapter<TuiJieBean.SoftwareBean> {
    public TuijianAdapter(Context context, List<TuiJieBean.SoftwareBean> datas) {
        super(context,  R.layout.tuijie_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final TuiJieBean.SoftwareBean softwareBean) {
        holder.setText(R.id.Text_One,softwareBean.getName());
        holder.setText(R.id.Text_Two,softwareBean.getDescription());


        holder.setOnclickListener(R.id.TuiJieLayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TuijianDetailActivity.class);
                intent.putExtra("id",softwareBean.getId());
                App.activity.startActivity(intent);
            }
        });


    }
}
