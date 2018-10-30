package com.example.lenovo.oschina.fragmant;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.SearchBean;

import java.util.List;

/**
 * Created by admin on 2017/4/17.
 */

public class FindAdapter extends BaseAdapter <SearchBean.ResultBean>{

    public FindAdapter(Context context, List<SearchBean.ResultBean> datas) {
        super(context, R.layout.tuijie_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, SearchBean.ResultBean resultBean) {
        holder.setText(R.id.Text_One,resultBean.getTitle());
        holder.setText(R.id.Text_Two,resultBean.getDescription());
    }
}
