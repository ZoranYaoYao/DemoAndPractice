package com.example.notification_demo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.logging.LogRecord;

/**
 * Created by zqs on 2018/11/27.
 * 问题点： 当杀死程序时，ScreenStatusReceiver就被杀死，导致不会有通知发送
 */
public class MonitorManager {

    public static boolean offscreen = false;
    private static MonitorManager instance = new MonitorManager();

    public static MonitorManager getInstance() {
        return instance;
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            sendNotification();
        }
    };

    public void delaySendNotification(){
        Message msg = Message.obtain();
        handler.sendMessageDelayed(msg, 3000);
    }

    int i = 0;
    public void sendNotification() {
        /**
         * ⭐️⭐️⭐️⭐️info5: 对阵通知栏传递传递应用层的context， 因为源码只是用context去获取项目资源id
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(BaseApplication.getContext());
        /**
         * info6: 通知栏跟新操作， 通过重新发送一个同一个id，不同内容的Notification进行替换
         */
        builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle("这是标题" + (i++)).setContentText("这是内容" + (i++));
        builder.setDefaults(Notification.DEFAULT_SOUND);
        /**
         * info3: 点击事件的处理 PendingIntent
         */
        Intent intent = new Intent(BaseApplication.getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);   //info4: 点击通知栏消失

        NotificationManagerCompat manager = NotificationManagerCompat.from(BaseApplication.getContext());
        //        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        /**
         * info1： 系统兼容问题，必须把targetSdkVersion 设置成<26 才能发出通知
         */
        /**
         * info2： notify(int id,..） 是对该id进行发送， 如果id不同，则会发送多条通知。
         */
        //        manager.notify(i++, builder.build());
        manager.notify(0, builder.build());
    }
}
