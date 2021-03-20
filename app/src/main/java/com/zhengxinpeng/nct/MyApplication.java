package com.zhengxinpeng.nct;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "be0d341e28555a36154bfbbf6f3e8db1");
    }
}
