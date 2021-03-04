package com.example.mydict.utils;

import android.app.Application;
import android.util.Log;

import org.xutils.x;

import static android.content.ContentValues.TAG;

public class UniteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); // 初始化xUtils 的模块；
        Log.i(TAG, "onCreate: "+ "初始化xUtils 的模块");
    }
}
