package com.example.lenovo.oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.lenovo.oschina.App;
import com.example.lenovo.oschina.R;
import com.example.lenovo.oschina.activity.ZixunXiangqing;
import com.example.lenovo.oschina.activity.zixunxiangqing.BokeXiangqing;
import com.example.lenovo.oschina.modle.enitity.ItemBoke;
import com.example.lenovo.oschina.modle.enitity.xiangqing.ZixunxiangqingBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/4/13.
 */

public class BoKeAdapter extends BaseAdapter<ItemBoke.BlogBean> {
    private String SystemDate;
    public BoKeAdapter(Context context, List<ItemBoke.BlogBean> datas) {
        super(context, R.layout.new_content_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final ItemBoke.BlogBean blogBean) {
          holder.setText(R.id.contentTitle,blogBean.getTitle());
          holder.setText(R.id.contentBody,blogBean.getBody());
        holder.setText(R.id.contentpinglun,blogBean.getCommentCount());
          holder.setOnclickListener(R.id.ZiXunLayout_Item, new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent  intent=new Intent(context, BokeXiangqing.class);
                  intent.putExtra("id",blogBean.getId());
                  intent.putExtra("count",blogBean.getCommentCount());
                  App.activity.startActivity(intent);
              }
          });
        SystemDate = getDate(System.currentTimeMillis(),"yyyy-MM-dd");//得到当前年月日

        long todayLIngCheng = getMorning(new Date()).getTime(); //今天的凌晨时间
        long newSTime = getDate(blogBean.getPubDate(), "yyyy-MM-dd HH:mm:ss");//发布的时间
        long systemLong = new Date(System.currentTimeMillis()).getTime();//当前的时间
        long newsDate = getDate(blogBean.getPubDate(), "yyyy-MM-dd");//获取到发布的日期
        long D_Date = getDate(SystemDate, "yyyy-MM-dd");//当前日期
        if (newSTime != 0) {
            long poortime = systemLong - newSTime;//发布的时间距离现在的时间相差多少毫秒
            long poor_s = poortime / 1000;  //现在就是相差多少秒
            if (poor_s < 60) {
                holder.setText(R.id.contentzuozhe, poor_s + "秒前");
            } else if (poor_s < 3600) {
                holder.setText(R.id.contentzuozhe, poor_s / 60 + "分钟前");
            } else if (newSTime > todayLIngCheng) {
                holder.setText(R.id.contentzuozhe, poor_s / 3600 + "小时前");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 1) {
                holder.setText(R.id.contentzuozhe, "昨天");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 2) {
                holder.setText(R.id.contentzuozhe, "前天");
            } else {
                holder.setText(R.id.contentzuozhe, Integer.parseInt(getDate((D_Date - newsDate), "d")) + "天前");
            }

//            holder.setText(R.id.TweetTime, blogBean.getPubDate());
        }
    }
    //获取今天凌晨时间
    private  Date getMorning(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    private String getDate(long time,String geshi){
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        Date date = new Date(time);
        return sdf.format(date);
    }
    private Long getDate(String strTime,String geshi){
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        try {
            return sdf.parse(strTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
