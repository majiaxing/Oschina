package com.example.lenovo.oschina.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;
import com.example.lenovo.oschina.modle.enitity.ItemHuodong;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/17.
 */

public class HuodongListViewAdapter extends BaseAdapter {
    private List<ItemHuodong.EventBean> mList;
    private Context mContext;

    public HuodongListViewAdapter(List<ItemHuodong.EventBean> mList, Context mContext) {
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
        private TextView Title,cover,startTime;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       Holder holder;
        if (convertView == null){
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.item_listview_boke,null);
            holder.Title = (TextView) convertView.findViewById(R.id.ItemTwo_title);
            holder.cover = (TextView) convertView.findViewById(R.id.ItemTwo_Body);
            holder.startTime = (TextView) convertView.findViewById(R.id.ItemTwo_authorname);

            convertView.setTag(holder);

        }else{
            holder = (Holder) convertView.getTag();
        }
        ItemHuodong.EventBean item = mList.get(position);
        holder.Title.setText(item.getTitle()+"");
        holder.cover.setText(item.getCover()+"");
        holder.startTime.setText(item.getStartTime()+"");
        return convertView;
    }
}
