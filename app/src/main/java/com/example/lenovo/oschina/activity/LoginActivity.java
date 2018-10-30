package com.example.lenovo.oschina.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.HomeActivity;
import com.example.lenovo.oschina.MainActivity;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.base.BaseFragment;
import com.example.lenovo.oschina.coefig.FragmantBuilder;
import com.example.lenovo.oschina.fragmant.dongtan.MineFragment;
import com.example.lenovo.oschina.modle.enitity.LoginBean;
import com.example.lenovo.oschina.modle.http.biz.LoginModel;
import com.example.lenovo.oschina.modle.http.biz.LoginModelImp;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.security.AccessController.getContext;

/**
 * Created by Lenovo on 2017/5/18.
 */
public class LoginActivity extends BaseFragment implements NetWorkCallBack{
    @BindView(R.id.Back)
    ImageView Back;
    @BindView(R.id.Login_Name)
    EditText LoginName;
    @BindView(R.id.Login_Password)
    EditText LoginPassword;
    @BindView(R.id.Login_WangjiMima)
    TextView LoginWangjiMima;
    @BindView(R.id.Login_Btn)
    Button LoginBtn;
    @BindView(R.id.Register_Login)
    Button RegisterLogin;
    private LoginModel loginModel;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;


    @Override
    protected int layoutId() {
        return R.layout.denglu_activity;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        loginModel = new LoginModelImp();
        mShared =App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
    }

    @Override
    public void setParams(Bundle bundle) {

    }


    @OnClick({R.id.Back, R.id.Login_WangjiMima, R.id.Login_Btn, R.id.Register_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Back:
                App.activity.onBackPressed();
                break;
            case R.id.Login_WangjiMima:

            break;
            case R.id.Login_Btn:
                String name = LoginName.getText().toString().trim();
                String password = LoginPassword.getText().toString().trim();
                loginModel.Login(name,password,"1",this);



                break;
            case R.id.Register_Login:
                break;
        }
    }

    @Override
    public void onSuccess(String xmlData) {
        Log.e("asd","请求到的数据"+xmlData);
        XStream xStream = new XStream();
        xStream.alias("oschina", LoginBean.class);
        LoginBean login = (LoginBean) xStream.fromXML(xmlData);



        if ("1".equals(login.getResult().getErrorCode())) {
            Bundle bundle = new Bundle();
            bundle.putString("name", login.getUser().getName());
            bundle.putString("por", login.getUser().getPortrait());
            mEditor.putString("UserName", LoginName.getText().toString());
            mEditor.putString("Password", LoginName.getText().toString());
            mEditor.putString("name", login.getUser().getName());
            mEditor.putString("uid", login.getUser().getUid());
            mEditor.commit();

            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
            FragmantBuilder.getInstance().start(MineActivity.class).setParams(bundle);

        }else if ("0".equals(login.getResult().getErrorCode())) {
            Toast.makeText(getContext(), "没有此用户", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onError(String errorMsg) {

    }




}
