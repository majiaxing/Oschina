package com.example.lenovo.oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.oschina.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by Lenovo on 2017/5/9.
 */

public class TestNormalAdapter extends StaticPagerAdapter {

    private int[] imgs = {
            R.drawable.cc,
            R.drawable.dd,
            R.drawable.aa,
            R.drawable.bb,
    };


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    @Override
    public int getCount() {
        return imgs.length;
    }
}


