package com.example.lenovo.oschina.adapter.fenlei;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.fenlei.ErjiFenleiBean;
import com.example.lenovo.oschina.modle.enitity.fenlei.FenLeiBean;

import java.util.List;

import static com.example.lenovo.oschina.R.id.ee;

/**
 * Created by Lenovo on 2017/5/25.
 */

public class MyErjiAdapter extends BaseAdapter {

    private List<ErjiFenleiBean.SoftwareBean> mList;
    private Context mContext;

    public MyErjiAdapter(List<ErjiFenleiBean.SoftwareBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class Holder{
        private TextView Tilte,body;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       Holder holder;
        if (convertView == null){
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.erjifenlei_item,null);
            holder.Tilte = (TextView) convertView.findViewById(R.id.erjifenlei_title);
            holder.body = (TextView) convertView.findViewById(R.id.erjifenlei_body);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        ErjiFenleiBean.SoftwareBean fenlei= mList.get(position);
        holder.Tilte.setText(fenlei.getName());
        holder.body.setText(fenlei.getDescription());

        return convertView;
    }
}
