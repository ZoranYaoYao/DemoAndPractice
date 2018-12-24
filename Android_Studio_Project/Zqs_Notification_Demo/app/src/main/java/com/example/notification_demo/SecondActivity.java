package com.example.notification_demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.notification_demo.toast.MyService;
import com.example.notification_demo.toast.TipUtil;

/**
 * Created by zqs on 2018/12/7.
 */
public class SecondActivity extends Activity {
    private MyService.MyBinder myBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e("zqs", "SecondActivity onCreate");
        findViewById(R.id.btn_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zqs", "btn_toast clicked");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                TipUtil.show(SecondActivity.this, "这是内容", "这是标题", null, null);

                myBinder.binderSendTips();
            }
        });

        Intent intent = new Intent(SecondActivity.this, MyService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MyService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("zqs", "SecondActivity onNewIntent");
    }
}
