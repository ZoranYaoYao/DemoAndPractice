package com.example.notification8;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * refer: https://blog.csdn.net/guolin_blog/article/details/79854070
 * <p>
 * 小米6x 8.1 问题：
 * 关闭通知栏之后， 在系统达到渠道通知栏， 但是就是不生效！
 * 卸载，重新安装就好了。。。
 * <p>
 * 通知栏bring up8.0的重要API
 * 1. 创建通知栏渠道
 * @TargetApi(Build.VERSION_CODES.O)
 * private void createNotificationChannle(String channleId, String channleName, int importance) {
 * NotificationChannel channel = new NotificationChannel(channleId, channleName, importance);
 * notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
 * notificationManager.createNotificationChannel(channel);
 * }
 * <p>
 * 2. 创建通知时，赋值通知栏渠道
 * Notification notification = new NotificationCompat.Builder(this, channleId)
 */
public class MainActivity extends AppCompatActivity {

    private String channleId;
    private String subChannelId;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channleId = "chat";
            String channleName = "聊天信息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannle(channleId, channleName, importance);

            subChannelId = "subscribe";
            channleName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannle(subChannelId, channleName, importance);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannle(String channleId, String channleName, int importance) {
        NotificationChannel channel = new NotificationChannel(channleId, channleName, importance);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 发送聊天消息
     */
    public void sendChatMsg(View view) {

        /**
         * 获取通知渠道状态
         */
        NotificationChannel channel = notificationManager.getNotificationChannel(channleId);
        if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
            startActivity(intent);
            Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
        } else {

            Notification notification = new NotificationCompat.Builder(this, channleId)
                    .setContentTitle("收到一条聊天信息")
                    .setContentText("今天中午吃什么")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(1, notification);
        }

    }


    /**
     * 发送订阅通知
     */
    public void sendSubscribeMsg(View view) {
        NotificationChannel channel = notificationManager.getNotificationChannel(subChannelId);
        if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
            startActivity(intent);
            Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
        } else {
            Notification notification = new NotificationCompat.Builder(this, subChannelId)
                    .setContentTitle("收到一条订阅消息")
                    .setContentText("地铁沿线30万商铺抢购中！")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(2, notification);
        }

    }


}
