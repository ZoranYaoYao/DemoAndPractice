package com.example.zqs.zqs_baidusdk_demo.services;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.*;

/**
 * Created by Zoran on 2018/1/16.
 * 接入百度SDK，管理定位服务
 */

public class LocationService {
    private LocationClient mClient;
    private LocationClientOption mClientOption;
    private Object objLock = new Object();

    public LocationService(Context applicationContext) {
        if (mClient == null) {
            /**zqs 全局对象，添加同步锁*/
            synchronized (objLock) {
                mClient = new LocationClient(applicationContext);
                mClient.setLocOption(getDefaultLocOption());
            }
        }

    }

    /***
     *
     * @param listener
     * @return
     */
    public boolean registerListener(BDAbstractLocationListener listener){
        boolean isSuccess = false;
        if(listener != null){
            mClient.registerLocationListener(listener);
            isSuccess = true;
        }
        return  isSuccess;
    }

    public void start(){
        synchronized (objLock) {
            if(mClient != null && !mClient.isStarted()){
                mClient.start();
            }
        }
    }
    public void stop(){
        synchronized (objLock) {
            if(mClient != null && mClient.isStarted()){
                mClient.stop();
            }
        }
    }

    private LocationClientOption getDefaultLocOption() {
        if (mClientOption == null) {
            mClientOption = new LocationClientOption();
            mClientOption.setLocationMode(LocationMode.Hight_Accuracy);
            mClientOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            mClientOption.setScanSpan(3000);
            mClientOption.setIsNeedAddress(true);
            mClientOption.setIsNeedLocationDescribe(true);
            mClientOption.setNeedDeviceDirect(false);
            mClientOption.setLocationNotify(false);
            mClientOption.setIgnoreKillProcess(false);
            mClientOption.setIsNeedLocationPoiList(true);
            mClientOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            mClientOption.setOpenGps(true);//可选，默认false，设置是否开启Gps定位
            mClientOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }

        return mClientOption;
    }
}
