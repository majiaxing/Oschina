package com.example.lenovo.oschina.activity;


import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.HomeActivity;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.adapter.DongTanFragmentAdapter;
import com.example.lenovo.oschina.base.BaseActivity;

import android.content.Intent;
import android.graphics.Bitmap;
        import android.support.design.widget.TabLayout;
        import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
        import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
        import android.support.v4.view.ViewPager;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.target.BitmapImageViewTarget;

import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.coefig.DataTimeUitls;
import com.example.lenovo.oschina.fragmant.dongtan.DongTanPingLunFragment;
import com.example.lenovo.oschina.fragmant.dongtan.DongTanZanFragment;
import com.example.lenovo.oschina.fragmant.dongtan.ZuixinFragmant;
import com.example.lenovo.oschina.modle.enitity.dongtan.DongTanBean;

import java.util.ArrayList;
        import java.util.List;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;

/**
 * Created by Lenovo on 2017/5/17.
 */
public class DongTanDetailActivity extends BaseActivity {
    @BindView(R.id.item_newsdongtan_author_head)
    ImageView itemNewsdongtanAuthorHead;
    @BindView(R.id.item_newsdongtan_author_name)
    TextView itemNewsdongtanAuthorName;
    @BindView(R.id.item_newsdongtan_author_body)
    TextView itemNewsdongtanAuthorBody;
    @BindView(R.id.item_newsdongtan_author_ImageView)
    ImageView itemNewsdongtanAuthorImageView;
    @BindView(R.id.item_newsdongtan_author_date)
    TextView itemNewsdongtanAuthorDate;
    @BindView(R.id.item_newsdongtan_author_phone)
    TextView itemNewsdongtanAuthorPhone;
    @BindView(R.id.item_newsdongtan_author_zhuanfa)
    TextView itemNewsdongtanAuthorZhuanfa;
    @BindView(R.id.item_newsdongtan_author_pinlun)
    TextView itemNewsdongtanAuthorPinlun;
    @BindView(R.id.item_newsdongtan_author_zan)
    TextView itemNewsdongtanAuthorZan;
    @BindView(R.id.sd)
    RelativeLayout sd;
    @BindView(R.id.Back)
    ImageView Back;
    @BindView(R.id.ee)
    LinearLayout ee;
    private DongTanZanFragment zanFragment;
    private List<String> titles;
    private List<BaseFragment> fragment;
    private DongTanFragmentAdapter fragmentAdapter;
    @BindView(R.id.dongtan_lin)
    RelativeLayout dongtanLin;
    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private Intent mIntent;
    private String data;
    private ArrayList<DongTanBean.TweetBean.UserBean> list;


    @Override
    protected int getlayoutId() {
        return  R.layout.activity_dong_tan_detail;
    }

    @Override
    protected void init() {
        mIntent = getIntent();

        getData();
        inItData();
    }

    private void inItData() {
        titles = new ArrayList<>();
        titles.add("赞");
        titles.add("评论");
        fragment = new ArrayList<>();
        zanFragment=new DongTanZanFragment();
        zanFragment.setList(list);
        fragment.add(zanFragment);
        fragment.add(new DongTanPingLunFragment());

        fragmentAdapter = new DongTanFragmentAdapter(getSupportFragmentManager(), fragment, titles);
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(fragmentAdapter);
        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    private void getData() {
        list = mIntent.getParcelableArrayListExtra("zanlist");
////        mList.addAll(list);

        //    String id=mIntent.getStringExtra("id");
        //    Log.i("DongTanDetailActivity",id);
        String por = mIntent.getStringExtra("por");
        String name = mIntent.getStringExtra("name");
        String body = mIntent.getStringExtra("body");
        String image = mIntent.getStringExtra("image");
        final String time = mIntent.getStringExtra("time");
        String likecount = mIntent.getStringExtra("likecount");
        String phone = mIntent.getStringExtra("phone");
//
//        mList.addAll(list);
//        mAdapter=new DongTanZanAdapter(this,mList);
        itemNewsdongtanAuthorName.setText(name);
        itemNewsdongtanAuthorBody.setText(body);
        itemNewsdongtanAuthorDate.setText(time);
        Glide.with(this).load(por).asBitmap().centerCrop().into(new BitmapImageViewTarget(itemNewsdongtanAuthorHead) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                ciDrawable.setCircular(true);
                itemNewsdongtanAuthorHead.setImageDrawable(ciDrawable);
            }
        });

        Glide.with(this).load(image).into(itemNewsdongtanAuthorImageView);
        data = DataTimeUitls.getData(time);
        itemNewsdongtanAuthorDate.setText(data);

    }





    @OnClick(R.id.Back)
    public void onViewClicked() {
        onBackPressed();
    }
}
