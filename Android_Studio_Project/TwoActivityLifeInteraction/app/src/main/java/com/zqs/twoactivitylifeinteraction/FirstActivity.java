package com.zqs.twoactivitylifeinteraction;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


/**
 * case 1: FirstAcitivity -> SecondActivity
 *         FirstAcitivity:SingleTask  SecondActivity: SingleTask
 *    或者  FirstAcitivity:singleTop   SecondActivity: sinleTop
     08-11 14:11:59.823 9561-9561/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onPause
     08-11 14:11:59.889 9561-9561/com.zqs.twoactivitylifeinteraction E/zqs1: SecondActivity excute --> onCreate
     08-11 14:11:59.892 9561-9561/com.zqs.twoactivitylifeinteraction E/zqs1: SecondActivity excute --> onStart
     08-11 14:11:59.892 9561-9561/com.zqs.twoactivitylifeinteraction E/zqs1: SecondActivity excute --> onResume
     08-11 14:12:00.399 9561-9561/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onStop

 * case 2: FirstAcitivity -> SecondActivity -> FirstAcitivity
 *         FirstAcitivity:SingleTask  SecondActivity: SingleTask
     08-11 14:22:39.255 14036-14036/com.zqs.twoactivitylifeinteraction E/zqs1: SecondActivity excute --> onPause
     08-11 14:22:39.265 14036-14036/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onNewIntent
     08-11 14:22:39.267 14036-14036/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onRestart
     08-11 14:22:39.268 14036-14036/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onStart
     08-11 14:22:39.269 14036-14036/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onResume
     08-11 14:22:39.735 14036-14036/com.zqs.twoactivitylifeinteraction E/zqs1: SecondActivity excute --> onStop
     08-11 14:22:39.736 14036-14036/com.zqs.twoactivitylifeinteraction E/zqs1: SecondActivity excute --> onDestroy

 * case 3: FirstAcitivity ->  FirstAcitivity
 *         FirstAcitivity:singleTop 或者 SingleTask
     08-11 14:28:09.009 16662-16662/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onPause
     08-11 14:28:09.009 16662-16662/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onNewIntent
     08-11 14:28:09.009 16662-16662/com.zqs.twoactivitylifeinteraction E/zqs1: FirstActivity excute --> onResume
 */
public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("zqs1", "FirstActivity excute --> onRestoreInstanceState");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_self).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
        Log.e("zqs1", "FirstActivity excute --> onCreate1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zqs1", "FirstActivity excute --> onRestart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("zqs1", "FirstActivity excute --> onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zqs1", "FirstActivity excute --> onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zqs1", "FirstActivity excute --> onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zqs1", "FirstActivity excute --> onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("zqs1", "FirstActivity excute --> onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zqs1", "FirstActivity excute --> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zqs1", "FirstActivity excute --> onDestroy");
    }
}
