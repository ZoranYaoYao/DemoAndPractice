package com.zqs_permission_util;

import android.app.Application;
import android.content.Context;

/**
 * Created by zqs on 2018/3/30.
 */

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        Utils.init(this);
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}