package com.zqs_spannable_util;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by zqs on 2018/3/30.
 */

public abstract class BaseBackActivity extends BaseActivity {
    protected CoordinatorLayout rootLayout;
    protected Toolbar mToolbar;
    protected AppBarLayout abl;
    protected FrameLayout flActivityContainer;

    @Override
    protected void setBaseView(int layouId) {
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_back, null);
        setContentView(contentView);
        rootLayout = findViewById(R.id.root_layout);
        abl = findViewById(R.id.abl);
        mToolbar = findViewById(R.id.toolbar);
        flActivityContainer = findViewById(R.id.activity_container);
        flActivityContainer.addView(LayoutInflater.from(this).inflate(layouId,flActivityContainer, false));
        setSupportActionBar(mToolbar);
        getToolBar().setDisplayHomeAsUpEnabled(true);

//        BarUtils.setStatusBarColor(this, ContextCompat.getColor(UtilsApp.getInstance(), R.color.colorPrimary), 0);
//        BarUtils.addMarginTopEqualStatusBarHeight(rootLayout);
    }

    protected ActionBar getToolBar(){
        return getSupportActionBar();
    }
}
