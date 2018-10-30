package com.example.lenovo.oschina.fragmant.sosuo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.modle.db.Manager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/21.
 */
public class SearchListFragment extends BaseFragment {
    @BindView(R.id.Search_List)
    ListView SearchList;
    @BindView(R.id.Text_Clear)
    TextView TextClear;
    Unbinder unbinder;

    private List<String> mList = new ArrayList<>();
    private Manager manager;
    private MyAdapter mAdapter;
    private FragmentManager fragmentManager = App.activity.getSupportFragmentManager();
    private FragmentTransaction transaction;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;


    @Override
    protected int layoutId() {
        return R.layout.search_list;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {
        SearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=mList.get(position);
                mEditor.putString("name",s);
                mEditor.commit();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.Search_FrameLayout,new SearchFragment());
                transaction.commit();
            }
        });
    }


    @Override
    protected void initData() {
        manager = new Manager(getActivity().getApplicationContext());
        mList = manager.getList();
        mAdapter = new MyAdapter();
        if (mList!=null){
            SearchList.setAdapter(mAdapter);
            TextClear.setVisibility(View.VISIBLE);
        }else {
            TextClear.setText("");
        }
        mShared=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor=mShared.edit();
    }



    @Override
    protected void loadData() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }




    @OnClick(R.id.Text_Clear)
    public void onViewClicked() {

        mList.clear();
        manager.delete();
        mAdapter.notifyDataSetChanged();



    }

    class MyAdapter extends BaseAdapter{
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
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.list_item, null);
                holder.mText = (TextView) convertView.findViewById(R.id.ListView_Text);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            String ss = mList.get(position);
            holder.mText.setText(ss + "");
            return convertView;
        }

        class Holder {
            private TextView mText;

        }
    }
}
