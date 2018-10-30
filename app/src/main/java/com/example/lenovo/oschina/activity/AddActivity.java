package com.example.lenovo.oschina.activity;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.modle.http.biz.DongTanImpl;
import com.example.lenovo.oschina.modle.http.biz.IDongTanModel;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2017/5/19.
 */
public class AddActivity extends BaseActivity {

    @BindView(R.id.Back)
    ImageView Back;
    @BindView(R.id.Title_Text)
    TextView TitleText;
    @BindView(R.id.Title_fasong)
    TextView TitleFasong;
    @BindView(R.id.Top_Group)
    RelativeLayout TopGroup;
    @BindView(R.id.add_content)
    EditText addContent;
    @BindView(R.id.Add_image)
    ImageView AddImage;
    @BindView(R.id.Add_at)
    ImageView AddAt;
    @BindView(R.id.Add_jin)
    ImageView AddJin;
    @BindView(R.id.Add_biaoqing)
    ImageView AddBiaoqing;

    private IDongTanModel model;
    private String Uid;
    @Override
    protected int getlayoutId() {
        return R.layout.add_activity;
    }

    @Override
    protected void init() {

        SharedPreferences mShared=getSharedPreferences("data",MODE_PRIVATE);
        String uid=mShared.getString("uid","");
        this.Uid=uid;
        Log.i("uid",uid);
        model=new DongTanImpl();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }



    @OnClick({R.id.Back, R.id.Title_fasong, R.id.Add_image, R.id.Add_at, R.id.Add_jin, R.id.Add_biaoqing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Back:
                App.activity.onBackPressed();
                break;
            case R.id.Title_fasong:
                if (addContent.getText().toString().isEmpty()){
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                }else {
                    faBiao();
                    addContent.setText("");
                }

                break;
            case R.id.Add_image:
                break;
            case R.id.Add_at:
                break;
            case R.id.Add_jin:
                break;
            case R.id.Add_biaoqing:
                break;
        }
    }

    private  void  faBiao() {
        model.faBiao(Uid, addContent.getText().toString(), new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {
                Log.i("消息", xmlData);

                finish();
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

}
