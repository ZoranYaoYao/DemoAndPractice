package com.zqs.rippleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RippleUpdateView view_ripple;
    TextView tv_monitor_stop;
    TextView tv_monitor_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_ripple = findViewById(R.id.view_ripple);
        tv_monitor_start = findViewById(R.id.tv_monitor_start);
        tv_monitor_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_ripple.start();
                tv_monitor_stop.setVisibility(View.VISIBLE);
                tv_monitor_start.setVisibility(View.GONE);
            }
        });

        tv_monitor_stop = findViewById(R.id.tv_monitor_stop);
        tv_monitor_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_ripple.stop();
                tv_monitor_stop.setVisibility(View.GONE);
                tv_monitor_start.setVisibility(View.VISIBLE);
            }
        });

        tv_monitor_stop.setVisibility(View.GONE);
        tv_monitor_stop.post(new Runnable() {
            @Override
            public void run() {
                int flag = tv_monitor_stop.getVisibility();
                Log.e("zqs1", "tv_monitor_stop.post flag = " + flag);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        view_ripple.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        view_ripple.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view_ripple.stop();
    }
}
