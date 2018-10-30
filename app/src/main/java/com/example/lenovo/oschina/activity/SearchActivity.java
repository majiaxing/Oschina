package com.example.lenovo.oschina.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.fragmant.sosuo.SearchFragment;
import com.example.lenovo.oschina.fragmant.sosuo.SearchListFragment;
import com.example.lenovo.oschina.modle.db.Manager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2017/5/21.
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.Search_Name)
    EditText SearchName;
    @BindView(R.id.Search_Clear)
    TextView SearchClear;
    @BindView(R.id.Search_FrameLayout)
    FrameLayout SearchFrameLayout;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;
    private Manager manager;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private String name;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;


    @Override
    protected int getlayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {

        Log.e("TAG","进入");

        fragmentManager = getSupportFragmentManager();
        initFrag();
        String name = SearchName.getText().toString();
        this.name = name;
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
        manager = new Manager(this);
        SearchName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())){
                    SearchClear.setText("确定");
                }else{
                    SearchClear.setText("取消");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }



    @OnClick(R.id.Search_Clear)
    public void onViewClicked() {
        if(("取消").equals(SearchName.getText().toString())){

        }else {
            mEditor.putString("name", SearchName.getText().toString());
            mEditor.commit();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.Search_FrameLayout, new SearchFragment());
            transaction.commit();
        }



    }
    private void initFrag() {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Search_FrameLayout, new SearchListFragment());
        transaction.commit();
    }
}
