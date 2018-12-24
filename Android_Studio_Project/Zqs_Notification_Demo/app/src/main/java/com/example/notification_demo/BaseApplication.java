package com.example.notification_demo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zqs on 2018/11/27.
 */
public class BaseApplication extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
