package com.example.lenovo.oschina.activity.faxian;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.ZixunXiangqing;
import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.coefig.Urls;
import com.example.lenovo.oschina.modle.enitity.YaoYiYaoBean;
import com.example.lenovo.oschina.modle.http.HttpFactory;
import com.example.lenovo.oschina.modle.http.callback.NetWorkCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2017/5/20.
 */
public class YaoYiYaoActivity extends BaseActivity implements SensorEventListener{

    @BindView(R.id.yaoyiyao_text)
    TextView yaoyiyaoText;
    @BindView(R.id.yaoyiyao_head)
    ImageView yaoyiyaoHead;
    @BindView(R.id.yaoyiyao_title)
    TextView yaoyiyaoTitle;
    @BindView(R.id.yaoyiyao_lin)
    LinearLayout yaoyiyaoLin;
    private SensorManager sensorManager;
    private Sensor defaultSensor;
    private boolean isShake = false;
    private Vibrator mVibrator;
    private SoundPool mSoundPool;
    private int load;
    private YaoYiYaoBean yaoYiYaoBean;



    @Override
    protected int getlayoutId() {
        return R.layout.activity_yao_yi_yao;
    }

    @Override
    protected void init() {
        mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        load = mSoundPool.load(this, R.raw.shake, 1);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            //获取加速度传感器
            defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (defaultSensor != null) {
                sensorManager.registerListener((SensorEventListener) this, defaultSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }


    @Override
    protected void onPause() {
        // 务必要在pause中注销 mSensorManager
        // 否则会造成界面退出后摇一摇依旧生效的bug
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        super.onPause();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            //获取三个方向值
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            if ((Math.abs(x) > 17 || Math.abs(y) > 17 || Math
                    .abs(z) > 17) && !isShake) {
                isShake = true;
                // TODO: 2016/10/19 实现摇动逻辑, 摇动后进行震动
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        Log.d("摇动", "onSensorChanged: 摇动");
                        handler.sendEmptyMessage(1);
                        handler.sendEmptyMessage(2);
                    }
                };
                thread.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    YaoYiYaoActivity.this.mVibrator.vibrate(300);


                    break;
                case 2:
                    HttpFactory.create().get(Urls.YaoYiYao, new HashMap<String, String>(), new NetWorkCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            XStream xStream = new XStream();
                            xStream.alias("oschina", YaoYiYaoBean.class);
                            yaoYiYaoBean = (YaoYiYaoBean) xStream.fromXML(result);
                            Glide.with(YaoYiYaoActivity.this).load(yaoYiYaoBean.getImage()).into(yaoyiyaoHead);
                            yaoyiyaoTitle.setText(yaoYiYaoBean.getDetail());
                            YaoYiYaoActivity.this.mSoundPool.play(YaoYiYaoActivity.this.load, 1, 1, 0, 0, 1);
                            isShake = false;
                        }

                        @Override
                        public void onError(String MsgError) {

                        }
                    });

                    break;

            }
        }
    };


    @OnClick(R.id.yaoyiyao_lin)
    public void onViewClicked() {
        if (TextUtils.isEmpty(yaoyiyaoTitle.getText())) {
            Toast.makeText(this, "请先摇一摇", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(YaoYiYaoActivity.this,ZixunXiangqing.class);
        intent.putExtra("url", yaoYiYaoBean.getUrl());
//        intent.putExtra(Keys.WEB_NAME, "摇一摇");
        startActivity(intent);


    }

}
