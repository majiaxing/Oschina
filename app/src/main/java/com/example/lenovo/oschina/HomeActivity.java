package com.example.lenovo.oschina;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lenovo.oschina.activity.AddActivity;
import com.example.lenovo.oschina.activity.DongtanFragment;
import com.example.lenovo.oschina.activity.FaxianActivity;
import com.example.lenovo.oschina.activity.LoginActivity;
import com.example.lenovo.oschina.activity.MineActivity;
import com.example.lenovo.oschina.activity.TableLayoutActivity;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.coefig.FragmantBuilder;


public class HomeActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "HomeActivity";
    private FrameLayout comtentGroup;
    private RadioButton homeZonghe;
    private RadioButton homeDongtan;
    private ImageView HomeImage;
    private RadioButton HomeFaxian;
    private RadioButton HomeMysef;
    private RadioGroup MainRadioGroup;
    private FragmentManager fragmentManager;
    private long mExitTime;
    private SharedPreferences mShared;
    private String uid;

    @Override
    protected int getlayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.i("TAG","onCreateView......");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void init() {
        fragmentManager = getSupportFragmentManager();
        homeZonghe = (RadioButton) findViewById(R.id.home_zonghe);
        homeDongtan = (RadioButton) findViewById(R.id.home_Dongtan);
        HomeImage = (ImageView) findViewById(R.id.Home_Image);
        HomeFaxian = (RadioButton) findViewById(R.id.Home_Faxian);
        HomeMysef = (RadioButton) findViewById( R.id.Home_Mysef);

        App.MainRadioGroup= (RadioGroup) findViewById(R.id.Main_RadioGroup);

        mShared=getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = mShared.edit();

        fragmentManager=getSupportFragmentManager();
    }

    @Override
    protected void initListener() {
        homeZonghe.setOnClickListener(this);
        homeDongtan.setOnClickListener(this);
        HomeImage.setOnClickListener(this);
        HomeFaxian.setOnClickListener(this);
        HomeMysef.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
       FragmantBuilder.getInstance().start(TableLayoutActivity.class);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_zonghe:
                FragmantBuilder.getInstance().start(TableLayoutActivity.class);
                break;
            case R.id.home_Dongtan:
                FragmantBuilder.getInstance().start(DongtanFragment.class);
                break;
            case R.id.Home_Image:
                uid=mShared.getString("uid","");
                Log.i("uid_______","");

                if (uid.isEmpty()){
                    FragmantBuilder.getInstance().start(LoginActivity.class);
                    App.MainRadioGroup.setVisibility(View.GONE);

                }else {
                    Intent  intent=new Intent(this,AddActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.Home_Faxian:
                FragmantBuilder.getInstance().start(FaxianActivity.class);
                break;
            case R.id.Home_Mysef:
                FragmantBuilder.getInstance().start(MineActivity.class);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
            String name = entry.getName();
            if ("TableLayoutActivity".equals(name) || "DongtanActivity".equals(name)
                    || "FaxianActivity".equals(name) || "MineActivity".equals(name)) {
                //System.exit(0);
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Object mHelperUtils;
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();

                } else {
                    finish();
                }
            } else {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    manager.popBackStackImmediate();
                    String fragmentName = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
                    BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
                    FragmantBuilder.getInstance().setLastFragment(fragment);
                } else {
                   super.onBackPressed();
                }
            }


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
