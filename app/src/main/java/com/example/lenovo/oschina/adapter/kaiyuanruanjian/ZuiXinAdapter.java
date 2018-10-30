package com.example.lenovo.oschina.adapter.kaiyuanruanjian;


import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.kaiyuanruanjian.TuiJieBean;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/24.
 */
public class ZuiXinAdapter extends BaseAdapter<TuiJieBean.SoftwareBean> {
    public ZuiXinAdapter(Context context, List<TuiJieBean.SoftwareBean> datas) {
        super(context,  R.layout.tuijie_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, TuiJieBean.SoftwareBean softwareBean) {
        holder.setText(R.id.Text_One,softwareBean.getName());
        holder.setText(R.id.Text_Two,softwareBean.getDescription());
    }
}
