package com.example.lenovo.oschina.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.Item;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/10.
 */

public class ListViewAdapter extends BaseAdapter {

   private List<Item.NewsBean> mList;
    private Context mContext;

    public ListViewAdapter(List<Item.NewsBean> mList, Context mContext) {
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
        private TextView Title,Body,authorname;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.item_listview_one,null);
            holder.Title = (TextView) convertView.findViewById(R.id.ItemOne_title);
            holder.Body = (TextView) convertView.findViewById(R.id.ItemOne_Body);
            holder.authorname = (TextView) convertView.findViewById(R.id.ItemOne_authorname);
            convertView.setTag(holder);

        }else{
            holder = (Holder) convertView.getTag();
        }
        Item.NewsBean item = mList.get(position);
        holder.Title.setText(item.getTitle()+"");
        holder.Body.setText(item.getBody()+"");
        holder.authorname.setText(item.getAuthor()+"");
        return convertView;
    }
}
