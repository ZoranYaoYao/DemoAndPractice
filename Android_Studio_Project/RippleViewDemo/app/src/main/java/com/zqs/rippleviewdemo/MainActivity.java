package com.zqs.rippleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MoveTextView.Callback {
    RippleUpdateViewAni view_ripple;
    TextView tv_monitor_stop;
    MoveTextView tv_monitor_start;


    TextView tv_monitor_state;

    int stateWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_ripple = findViewById(R.id.view_ripple);
        tv_monitor_start = findViewById(R.id.tv_monitor_start);
        tv_monitor_start.setCallback(this);
        tv_monitor_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_monitor_start.rightEnd == 0)
                    tv_monitor_start.rightEnd = tv_monitor_stop.getWidth();
                tv_monitor_start.setBackground(R.color.color_f8b442);
                tv_monitor_start.start(2);
            }
        });

        tv_monitor_stop = findViewById(R.id.tv_monitor_stop);
        tv_monitor_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_ripple.stop();
                tv_monitor_start.setVisibility(View.VISIBLE);
                tv_monitor_start.setBackground(R.color.color_777c8b);
                tv_monitor_start.start(1);
            }
        });

        tv_monitor_state = findViewById(R.id.tv_monitor_state);
        tv_monitor_state.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                stateWidth = tv_monitor_state.getWidth();
                tv_monitor_state.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public void animaOverCallback(View view) {
        switch (view.getId()) {
            case R.id.tv_monitor_start:
                view_ripple.start();
                tv_monitor_start.setVisibility(View.GONE);
                break;
            case R.id.tv_monitor_stop:
        }

    }
}
