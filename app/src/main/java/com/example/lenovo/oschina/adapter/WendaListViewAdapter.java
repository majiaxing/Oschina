package com.example.lenovo.oschina.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.ItemWenda;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/10.
 */

public class WendaListViewAdapter extends BaseAdapter {

    private List<ItemWenda.PostBean> mList;
    private Context mContext;

    public WendaListViewAdapter(List<ItemWenda.PostBean> mList, Context mContext) {
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
        private ImageView image;
        private TextView Title,Body,authorname;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Holder holder;
        if (convertView == null){
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.item_listview_wenda,null);
            holder.Title = (TextView) convertView.findViewById(R.id.ItemTthree_title);
            holder.Body = (TextView) convertView.findViewById(R.id.ItemThree_Body);
            holder.authorname = (TextView) convertView.findViewById(R.id.ItemThree_authorname);
            holder.image = (ImageView) convertView.findViewById(R.id.ItemThree_touxiang);
            convertView.setTag(holder);

        }else{
            holder = (Holder) convertView.getTag();
        }
        ItemWenda.PostBean item = mList.get(position);
        holder.Title.setText(item.getTitle()+"");
        holder.Body.setText(item.getBody()+"");
        holder.authorname.setText(item.getAuthor()+"");
        Glide.with(mContext).load(item.getPortrait()).into(holder.image);
        return convertView;


    }
}
