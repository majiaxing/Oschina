package com.example.lenovo.oschina.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/10.
 */

public class BokeListViewAdapter extends BaseAdapter {

    private List<ItemBoke.BlogBean> mList;
    private Context mContext;

    public BokeListViewAdapter(List<ItemBoke.BlogBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder{
        private TextView Title,Body,authorname;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.item_listview_boke,null);
            holder.Title = (TextView) convertView.findViewById(R.id.ItemTwo_title);
            holder.Body = (TextView) convertView.findViewById(R.id.ItemTwo_Body);
            holder.authorname = (TextView) convertView.findViewById(R.id.ItemTwo_authorname);

            convertView.setTag(holder);

        }else{
            holder = (Holder) convertView.getTag();
        }
        ItemBoke.BlogBean item = mList.get(position);
        holder.Title.setText(item.getTitle()+"");
        holder.Body.setText(item.getBody()+"");
        holder.authorname.setText(item.getAuthorname()+"");
        return convertView;
    }
}
