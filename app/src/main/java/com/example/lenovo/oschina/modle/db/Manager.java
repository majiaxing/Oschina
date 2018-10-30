package com.example.lenovo.oschina.modle.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/17.
 */

public class Manager {
    private MyHelper mHelper;
    private SQLiteDatabase mDb;
    private Context context;

    public Manager(Context context) {
        this.context = context;
        mHelper = new MyHelper(context, "yao.db", 1);
        mDb = mHelper.getWritableDatabase();
    }


    public boolean insert(String name) {
        boolean boo;
        ContentValues con = new ContentValues();
        con.put("name", name);
        long ins = mDb.insert("hehe", null, con);
        if (ins > 0) {
            boo = true;
        } else {
            boo = false;
        }
        return boo;
    }

    public void delete() {
        mDb.delete("hehe", null, null);
    }

    public List<String> getList() {
        List<String> mList = new ArrayList<>();
        Cursor cursor = mDb.query("hehe", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String ss = cursor.getString(cursor.getColumnIndex("name"));
            mList.add(ss);
        }
        return mList;
    }
}
