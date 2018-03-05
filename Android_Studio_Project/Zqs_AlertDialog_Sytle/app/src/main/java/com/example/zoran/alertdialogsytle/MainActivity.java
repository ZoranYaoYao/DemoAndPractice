package com.example.zoran.alertdialogsytle;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * http://blog.csdn.net/qq_34983989/article/details/51396051
 *
 * 问题一：
 *      button的按键背景与原生的背景显示不一致 。 没有paading处理
 *      【Solution】
 *      改变button的宽高来改变点击背景的大小
 * 问题二:
 *       处理视图事件是否在该View里面
 *  http://blog.csdn.net/dongzhout/article/details/53932822
 * 问题三：
 *      touch的监听事件的取消cancel操作：
 * 问题四： setonTouchListener called on it but does 触摸匿名监听类报警告
 *      不用管
 *      https://stackoverflow.com/questions/46135249/
 *      custom-view-imagebutton-has-setontouchlistener-called-on-it-but-does-not-overr
 *
 * */
public class MainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private  AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tvonclick(View view) {
        initDialog();
        initDialoStyle();
    }

    private void initDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("内容内容内容内容内容内容内容")
                .setPositiveButton("仍然确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确认已点击",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("稍后确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"稍后确认",Toast.LENGTH_SHORT).show();
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
    }


    private void initDialoStyle() {
        final Button positiveButton = alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
        initDialogButton(positiveButton);
        positiveButton.setTextColor(getResources().getColor(R.color.color_e79c1e));
        positiveButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int color = getResources().getColor(R.color.color_333333);
                switch (event.getAction()) {
                        case MotionEvent.ACTION_CANCEL:
                            color = getResources().getColor(R.color.color_e79c1e);
                }
                positiveButton.setTextColor(color);
                return  false;
            }
        });

        Button negativeButton = alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        initDialogButton(negativeButton);
    }

    //(x,y)是否在view的区域内
    private boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

    private void initDialogButton(Button button) {
        button.setTextColor(getResources().getColor(R.color.color_333333));
        button.setBackgroundResource(R.drawable.selector_positive_style);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.gravity = Gravity.CENTER;
        //显示效果更小一点
        params.height = DisplayUtil.dip2px(this,38);
        button.setLayoutParams(params);
    }
}
