package com.zqs.twoactivitylifeinteraction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
        Log.e("zqs1", "SecondActivity excute --> onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zqs1", "SecondActivity excute --> onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zqs1", "SecondActivity excute --> onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zqs1", "SecondActivity excute --> onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zqs1", "SecondActivity excute --> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zqs1", "SecondActivity excute --> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zqs1", "SecondActivity excute --> onDestroy");
    }
}
