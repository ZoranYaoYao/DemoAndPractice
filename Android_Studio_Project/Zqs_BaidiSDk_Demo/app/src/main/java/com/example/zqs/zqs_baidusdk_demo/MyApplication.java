package com.example.zqs.zqs_baidusdk_demo;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;
import com.example.zqs.zqs_baidusdk_demo.services.LocationService;

/**
 * Created by Zoran on 2018/1/16.
 */

public class MyApplication extends Application {
    private  LocationService mLocationService;

    @Override
    public void onCreate() {
        super.onCreate();

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        mLocationService = new LocationService(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
    }

    public  LocationService getLocationService() {
        return mLocationService;
    }
}
