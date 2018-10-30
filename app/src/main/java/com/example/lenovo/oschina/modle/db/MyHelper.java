package com.example.lenovo.oschina.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2017/4/17.
 */

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context, String name, int version) {
        super(context, name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String sql="create table hehe(name varchar(20) primary key)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
