package com.example.lenovo.oschina;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.lenovo.oschina.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends BaseActivity {


    @BindView(R.id.home_sousuo)
    Button mSousuo;
    @BindView(R.id.comtentGroup)
    FrameLayout comtentGroup;
    @BindView(R.id.home_zonghe)
    RadioButton mZonghe;
    @BindView(R.id.home_Dongtan)
    RadioButton mDongtan;
    @BindView(R.id.Home_Image)
    ImageView mImage;
    @BindView(R.id.Home_Faxian)
    RadioButton mFaxian;
    @BindView(R.id.Home_Mysef)
    RadioButton mMysef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.home_sousuo, R.id.home_zonghe, R.id.home_Dongtan, R.id.Home_Image, R.id.Home_Faxian, R.id.Home_Mysef})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_sousuo:

                break;
            case R.id.home_zonghe:

                break;
            case R.id.home_Dongtan:
                break;
            case R.id.Home_Image:
                break;
            case R.id.Home_Faxian:
                break;
            case R.id.Home_Mysef:
                break;
        }
    }
}
