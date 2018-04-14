package com.zqs.slideconfict;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import java.util.Arrays;

/**
 * 由于横向滑动界面的一部分, 竖向滑动ScrollView 是全屏,一旦出去了, 就不受子View控制了
 * 该case,跟全屏的横竖项滑动有本质的区别!
 *
 * 问题一: 开始会有一截跳转
 * DOWN 事件 给定上一次的值
 *
 * 问题二: 划出界面的时候
 * 出发CANCEL事件, 此时跟UP事件处理的逻辑是一样的
 */
public class MainActivity extends AppCompatActivity {

    private SlideSwitchButton btnOrderScope;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOrderScope = findViewById(R.id.btn_order_scope);
        btnOrderScope.setTabIndex(1);
        btnOrderScope.setTabs(Arrays.asList("1.5", "3", "6","智能"));



    }
}
