package com.example.lenovo.oschina;

import android.app.Application;
import android.content.Context;
import android.widget.RadioGroup;

import com.example.lenovo.oschina.base.BaseActivity;
import com.example.lenovo.oschina.base.BaseFragment;

/**
 * Created by Lenovo on 2017/5/11.
 */

public class App extends Application {
    public static BaseActivity activity;
    public static BaseFragment lastfragment;
    public static RadioGroup MainRadioGroup;

}
