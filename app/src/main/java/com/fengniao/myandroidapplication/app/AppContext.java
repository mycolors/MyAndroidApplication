package com.fengniao.myandroidapplication.app;

import android.app.Application;
import android.os.Debug;

import org.xutils.BuildConfig;
import org.xutils.x;


public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //xutils初始化
        x.Ext.init(this);
        //是否输出debug日志，开启debug会影响性能
//        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
