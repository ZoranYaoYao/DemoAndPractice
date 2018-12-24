package com.example.notification_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zqs on 2018/11/27.
 */
public class ScreenStatusReceiver extends BroadcastReceiver {

    /**
     * 监听开关机状态
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        {
            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                MonitorManager.getInstance().delaySendNotification();
            } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            }


        }
    }
}
