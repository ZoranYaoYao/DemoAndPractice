package com.example.notification_demo.toast;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.notification_demo.SecondActivity;

/**
 * Created by zqs on 2018/12/7.
 */
public class MyService extends Service {

    public Context context;

    public MyBinder mBinder = new MyBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }



    public void sendTips() {
        TipUtil.show(this, "这是内容", "这是标题", null, null);
    }

    public class MyBinder extends Binder {

        public void binderSendTips() {
            sendTips();
        }
    }
}
