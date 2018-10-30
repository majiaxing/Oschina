package com.example.lenovo.oschina.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.mine.XiaoxiActivity;
import com.example.lenovo.oschina.adapter.GuanZhuFragment;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.coefig.FragmantBuilder;
import com.example.lenovo.oschina.fragmant.dongtan.MineFragment;
import com.example.lenovo.oschina.fragmant.mine.FenSiFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2017/5/19.
 */

public class MineActivity extends BaseFragment {


    @BindView(R.id.SheZhi)
    ImageView SheZhi;
    @BindView(R.id.Login_Image)
    ImageView LoginImage;
    @BindView(R.id.DianjiText)
    TextView DianjiText;
    @BindView(R.id.yi)
    TextView yi;
    @BindView(R.id.DongTan)
    RelativeLayout DongTan;
    @BindView(R.id.Er)
    TextView Er;
    @BindView(R.id.ShouCang)
    RelativeLayout ShouCang;
    @BindView(R.id.San)
    TextView San;
    @BindView(R.id.GuanZhu)
    RelativeLayout GuanZhu;
    @BindView(R.id.Si)
    TextView Si;
    @BindView(R.id.FenSi)
    RelativeLayout FenSi;
    @BindView(R.id.XianXing)
    LinearLayout XianXing;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.My_Xiaoxi)
    RelativeLayout MyXiaoxi;
    @BindView(R.id.blog)
    ImageView blog;
    @BindView(R.id.My_Boke)
    RelativeLayout MyBoke;
    @BindView(R.id.event)
    ImageView event;
    @BindView(R.id.My_WenDa)
    RelativeLayout MyWenDa;
    @BindView(R.id.question)
    ImageView question;
    @BindView(R.id.My_HuoDong)
    RelativeLayout MyHuoDong;
    @BindView(R.id.team)
    ImageView team;
    @BindView(R.id.My_TuanDui)
    RelativeLayout MyTuanDui;
    Unbinder unbinder;
    private String img = null;
    private String name;
    private String por;
    private String Uid;
    private SharedPreferences mShared;
    private String Size;
    private SharedPreferences.Editor mEditor;


    @Override
    protected int layoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(View view) {


        xiaoshi();
    }

    @Override
    protected void initData() {
        mShared = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        String uid = mShared.getString("uid", "");
        this.Uid = uid;
        String size = mShared.getString("size", "");
        this.Size = size;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void setParams(Bundle bundle) {

        name = bundle.getString("name");
        Log.e("name", name);
        por = bundle.getString("por");
        if (DianjiText != null)
            DianjiText.setText(name);
        //设置圆形图片；
        if(LoginImage!=null)
        Glide.with(getActivity().getApplicationContext()).load(por).asBitmap().centerCrop().into(new BitmapImageViewTarget(LoginImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                ciDrawable.setCircular(true);
                LoginImage.setImageDrawable(ciDrawable);
            }
        });
    }

    @Override
    public void onhidden() {

        updataTitleBar();


    }



    @OnClick({R.id.SheZhi, R.id.Login_Image, R.id.yi, R.id.Er, R.id.San, R.id.Si, R.id.My_Xiaoxi, R.id.My_Boke, R.id.My_WenDa, R.id.My_HuoDong, R.id.My_TuanDui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.SheZhi:
                mEditor = mShared.edit();
                mEditor.remove("uid");
                mEditor.commit();
                Toast.makeText(App.activity, "注销成功", Toast.LENGTH_SHORT).show();
                LoginImage.setImageResource(R.drawable.ic_nav_my_normal);
                DianjiText.setText("请点击头像登录");

                break;
            case R.id.Login_Image:

                if (img == null) {
                    FragmantBuilder.getInstance().start(LoginActivity.class);
                    App.MainRadioGroup.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "你已经登录不能再登录", Toast.LENGTH_SHORT).show();
                }
                this.getXianXing().setVisibility(View.VISIBLE);

                break;
            case R.id.yi:
                FragmantBuilder.getInstance().start(MineFragment.class);
                break;
            case R.id.Er:
                FragmantBuilder.getInstance().start(GuanZhuFragment.class);

                break;
            case R.id.San:


                break;
            case R.id.Si:
                FragmantBuilder.getInstance().start(FenSiFragment.class);
                Si.setText(Size);
                break;
            case R.id.My_Xiaoxi:
                Intent intent = new Intent(getContext(),XiaoxiActivity.class);
                startActivity(intent);
                break;
            case R.id.My_Boke:
                break;
            case R.id.My_WenDa:
                break;
            case R.id.My_HuoDong:
                break;
            case R.id.My_TuanDui:
                break;
        }
    }


    @Override
    protected void updataTitleBar() {
//           ((HomeActivity) App.activity).getTitleText().setText("我的");
//        if (((HomeActivity) App.activity).getBottomGroup().getVisibility() == View.GONE) {
//            (HomeActivity) App.activity).getBottomGroup().setVisibility(View.VISIBLE);
//        } else if (((HomeActivity) App.activity).getTopGroup().getVisibility() == View.GONE) {
//            ((HomeActivity) App.activity).getTopGroup().setVisibility(View.VISIBLE);
//        }


    }

    private void xiaoshi() {
        if (DianjiText.getText().toString().equals(name)) {
            this.getXianXing().setVisibility(View.VISIBLE);
        } else {
            this.getXianXing().setVisibility(View.GONE);
        }
    }


    public LinearLayout getXianXing() {
        return XianXing;
    }

    public LinearLayout setXianXing() {
        return XianXing;
    }
    @Override
    public void onShow() {
        super.onShow();
        App.MainRadioGroup.setVisibility(View.VISIBLE);
    }

}
