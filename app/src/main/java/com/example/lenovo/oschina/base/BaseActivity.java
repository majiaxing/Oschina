package com.example.lenovo.oschina.base;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.coefig.FragmantBuilder;

import butterknife.ButterKnife;

/**
 * Created by Lenovo on 2017/5/11.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private boolean mFlag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        this.fragmentManager =getSupportFragmentManager();
        ButterKnife.bind(this);
        init();
        initListener();

    }



    @Override
    protected void onResume() {
        super.onResume();
        App.activity = this;
        if(mFlag) {
            loadData();
        mFlag=false;
        }

    }

    /**
     * 加载布局
     * @return
     */
    protected abstract int getlayoutId();

    /**
     * 初始化布局
     */

    protected abstract void init();


    /**
     *
     * 初始化监听
     *
     * */
    protected abstract void initListener();

    /**
     *
     * 加载数据
     *
     * */
    protected abstract void loadData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}