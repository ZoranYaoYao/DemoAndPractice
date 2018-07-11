package com.example.zoran.zqs_platform_rewards;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;

import com.example.zoran.zqs_platform_rewards.databinding.ActivityThirdBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018-7-6.
 */

public class ThirdActivity extends AppCompatActivity {

    ActivityThirdBinding bind;
    private RecyclerView mExtraInfoView;
    private List<TaxiRideExtraInfo> mDataList;
    private CommonRecyclerViewAdapter mExraInfoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_third);
        mExtraInfoView = bind.recycleExtraInfo;
        setdata();
        setupExtraInfo();
    }

    private void setupExtraInfo() {
        //zqs 不设置LayoutManager 就不会走Adapter的 createViewHolder,所以是recycleView的必须方法
        // ListView 实现
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mExtraInfoView.setLayoutManager(layoutManager);

        // 如果我们想要一个GridView形式的RecyclerView，那么在LayoutManager上我们就要使用GridLayoutManager
        // 实例化一个GridLayoutManager，列数为3
        NoScrollGridLayoutManager layoutManager = new NoScrollGridLayoutManager(this, 3);
        layoutManager.setScrollEnabled(false);
        mExtraInfoView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mExtraInfoView.setHasFixedSize(true);
        mExtraInfoView.addItemDecoration(new VerticalItemDecoration(this, LinearLayoutManager.VERTICAL,
                DisplayUtil.dip2px(this, 1), ContextCompat.getColor(this, R.color.color_e6e6e6)));
        mExraInfoAdapter = new CommonRecyclerViewAdapter(mDataList, this);
        mExtraInfoView.setAdapter(mExraInfoAdapter);
    }

    public class NoScrollGridLayoutManager extends GridLayoutManager {
        private boolean isScrollEnabled = true;

        public NoScrollGridLayoutManager(Context context, int spanCount) {
            super(context,spanCount);
        }

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            return isScrollEnabled && super.canScrollVertically();
        }
    }

    class VerticalItemDecoration extends CommonItemDecoration {

        public VerticalItemDecoration(Context context, int orientation, int dividerHeight, int dividerColor) {
            super(context, orientation, dividerHeight, dividerColor);
        }

        @Override
        public void drawVertical(Canvas canvas, RecyclerView parent) {

            final int childSize = parent.getChildCount();
            for (int i = 0; i < childSize - 1; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getTop();
                final int bottom = child.getBottom();
                final int left = child.getRight() + layoutParams.rightMargin;
                final int right = left + mDividerHeight;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }
            }
        }
    }

    public SpannableStringBuilder defeatDriverSsb;
    private void setdata() {
        TaxiRideExtraInfo data1 = new TaxiRideExtraInfo();
        data1.extra_fee = 100;
        data1.LayoutType = 1;
//        data1.content = "调度费20元";
        defeatDriverSsb = new SpanUtils().append("调度费")
                .append(String.valueOf(20.5)).setForegroundColor(ContextCompat.getColor(this,R.color.color_E89D00))
                .append("元").setForegroundColor(ContextCompat.getColor(this,R.color.color_E89D00)).create();
        data1.content = defeatDriverSsb;

        TaxiRideExtraInfo data2 = new TaxiRideExtraInfo();
        data2.pick_by_meter = 1;
        SpannableStringBuilder defeatDriverSsb2 = new SpanUtils().append("平台奖励")
                .append(String.valueOf(16.5)).setForegroundColor(ContextCompat.getColor(this,R.color.color_E89D00))
                .append("元").setForegroundColor(ContextCompat.getColor(this,R.color.color_E89D00)).create();
        data2.content = defeatDriverSsb2;

        TaxiRideExtraInfo data3 = new TaxiRideExtraInfo();
        data3.platform_subsidy_cent = 100;
        SpannableStringBuilder defeatDriverSsb3 = new SpanUtils().append("打表来接").create();
        data3.content = defeatDriverSsb3;

        TaxiRideExtraInfo data4 = new TaxiRideExtraInfo();
        SpannableStringBuilder defeatDriverSsb4 = new SpanUtils().append("不参与活动").create();
        data4.content = defeatDriverSsb4;

        TaxiRideExtraInfo data5 = new TaxiRideExtraInfo();
        SpannableStringBuilder defeatDriverSsb5 = new SpanUtils().append("企业订单").create();
        data5.content = defeatDriverSsb5;

        mDataList = new ArrayList<>();
        mDataList.add(data1);
        mDataList.add(data2);
        mDataList.add(data3);
        mDataList.add(data4);
        mDataList.add(data5);

    }
}
