package com.example.lenovo.oschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.style.UpdateAppearance;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/11.
 */

public abstract class BaseFragment extends Fragment {

    private Bundle bundle;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(layoutId(), container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         unbinder= ButterKnife.bind(this, view);
        initView(view);
        initListener();
        initData();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        updataTitleBar();
        setGoneFragment();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            onhidden();

        }else{
            onShow();
        }
    }

    /**
     * 加载布局
     *
     * @return
     */
    protected abstract int layoutId();

    /**
     * 初始化视图
     *
     * */
    protected abstract void initView(View view);

    /**
     *
     * 初始化布局
     *
     * */
    protected abstract void initData();

    /**
     *
     * 初始化监听
     *
     * */
    protected  abstract void initListener();


    /**
     *
     * 加载数据
     *
     * */
    protected abstract void loadData();

    /**
     *
     * 当fragmant可见时
     *
     * */

    public void onShow(){
        setGoneFragment();
    }

    /**
     * 当fragmant不可见时
     *
     * */
    public void onhidden(){
        setGoneFragment();
    }


    protected void setGoneFragment() {

    }

/**
 * 页面却换传递数据
 *
 * */
    public abstract void setParams(Bundle bundle);

    /**
     * 更改标题的内容
     *
     * */
    protected void updataTitleBar(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
