package com.example.lenovo.oschina.adapter.fenlei;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.modle.enitity.fenlei.FenLeiBean;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/25.
 */


public class MyAdapter extends BaseAdapter {
        private List<FenLeiBean.SoftwareTypeBean> mList;
        private Context mContext;

    public MyAdapter(List<FenLeiBean.SoftwareTypeBean> mList, Context mContext) {
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder holder;
            if (convertView == null){
                holder = new Holder();
                convertView = View.inflate(mContext, R.layout.fenlei_item,null);
                holder.name = (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            FenLeiBean.SoftwareTypeBean fenlei = mList.get(position);
            holder.name.setText(fenlei.getName()+"");
            return convertView;
        }

        class Holder{
            private TextView name;
        }

    }


