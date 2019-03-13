package com.hcykj.mvpdemo.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class AppApplication extends Application {

    private static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Utils.init(this);
    }

    public static AppApplication getInstance(){
        return instance;
    }
}
