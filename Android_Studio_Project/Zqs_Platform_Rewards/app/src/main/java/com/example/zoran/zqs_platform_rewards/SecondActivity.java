package com.example.zoran.zqs_platform_rewards;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zoran.zqs_platform_rewards.databinding.ActivityMainBinding;
import com.example.zoran.zqs_platform_rewards.databinding.ActivityTwoBinding;

import java.util.ArrayList;

/**
 * Created by zqs on 2018/1/29.
 */

public class SecondActivity extends Activity {
    private LinearLayout mLinearExtraInfoView;

    TaxiRideExtraInfo data1;
    ArrayList<String> extraInfoList;

    private LinearLayout mLinearExtraInfo;
    private TextView mTvFirst;
    private TextView mTvSecond;
    private TextView mTvThird;
    private View mLineFirst;
    private View mLineSecond;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTwoBinding viewDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_two);
        mLinearExtraInfoView = viewDataBinding.llExtraInfo;
        setExtraInfoOrder();
        setupTaxiRideExtraInfo();

        mLinearExtraInfo = viewDataBinding.llTaxiRideInfo;
        mTvFirst = viewDataBinding.tvFirst;
        mTvSecond = viewDataBinding.tvSecond;
        mTvThird = viewDataBinding.tvThird;
        mLineFirst = viewDataBinding.lineFirst;
        mLineSecond = viewDataBinding.lineSecond;
        setupTaxiRideExtraInfo2();
    }

    private void setupTaxiRideExtraInfo2() {
        if(extraInfoList != null && extraInfoList.size() == 0) {
            mLinearExtraInfo.setVisibility(View.GONE);
            return;
        }

        if (extraInfoList.size() == 1) {
            setTextStyle(mTvFirst,extraInfoList.get(0));
            //重新设置布局参数
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvFirst.getLayoutParams();
            params.width =ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.weight = 0;
            params.setMargins(dip2px(22),0,0,0);
            mTvFirst.setLayoutParams(params);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
//                    ,ViewGroup.LayoutParams.MATCH_PARENT);
//            params.setMargins((int)dip2px(22.0f),0,0,0);
//            mTvFirst.setLayoutParams(params);
        } else if(extraInfoList.size() == 2) {
            setTextStyle(mTvFirst,extraInfoList.get(0));
            setTextStyle(mTvSecond,extraInfoList.get(1));
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0
//                    , ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvFirst.getLayoutParams();
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mTvSecond.getLayoutParams();
            mTvFirst.setGravity(Gravity.CENTER);
            mTvFirst.setLayoutParams(params);
            mTvSecond.setGravity(Gravity.CENTER);
            mTvSecond.setLayoutParams(params2);
            mTvThird.setVisibility(View.GONE);
            mLineFirst.setVisibility(View.VISIBLE);
        } else if(extraInfoList.size() == 3) {
            setTextStyle(mTvFirst,extraInfoList.get(0));
            setTextStyle(mTvSecond,extraInfoList.get(1));
            setTextStyle(mTvThird,extraInfoList.get(2));
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0
//                    , ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvFirst.getLayoutParams();
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) mTvSecond.getLayoutParams();
            LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) mTvThird.getLayoutParams();
            //mTvFirst.setGravity(Gravity.CENTER);
            mTvFirst.setLayoutParams(params);
            //mTvSecond.setGravity(Gravity.CENTER);
            mTvSecond.setLayoutParams(params2);
            //mTvThird.setGravity(Gravity.CENTER);
            mTvThird.setLayoutParams(params3);
            mLineFirst.setVisibility(View.VISIBLE);
            mLineSecond.setVisibility(View.VISIBLE);
        }

    }

    private void setTextStyle(TextView textView,String content) {
        SpannableString infoSs = new SpannableString(content);
        infoSs.setSpan(new StyleSpan(Typeface.BOLD),0,infoSs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(DigitUtil.ContainDigit(content)){
            String money = DigitUtil.getNumbers(content);
            int moneyStartIndex = DigitUtil.getNumbersStartIndex(content,money);
            //infoSs.setSpan(moneyColor,moneyStartIndex,moneyStartIndex+money.length()
            // ,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            infoSs.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843))
                    ,moneyStartIndex,infoSs.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(infoSs);
    }


    private void setExtraInfoOrder() {
        //默认顺序 调度费，平台奖励，打表来接，
        data1= new TaxiRideExtraInfo();
        data1.extra_fee = 100;
        data1.pick_by_meter = 1;
        data1.platform_subsidy_cent = 125;
        extraInfoList = new ArrayList<>();
        if (data1.extra_fee > 0) extraInfoList.add("调度费用"+data1.extra_fee+"元");
        float rewards = data1.platform_subsidy_cent/100.0f;
        if(data1.platform_subsidy_cent > 0) extraInfoList.add("平台奖励"+rewards+"元");
        if (data1.pick_by_meter == 1 ) extraInfoList.add("打表来接");
    }

    private void setupTaxiRideExtraInfo() {
        if (extraInfoList != null  && extraInfoList.size() == 0) {
            mLinearExtraInfoView.setVisibility(View.GONE);
            return;
        }
        mLinearExtraInfoView.setBackgroundColor(getResources().getColor(R.color.color_f5f5f5));
        for (int index = 0; index < extraInfoList.size(); index++) {
            addView(extraInfoList.get(index), index);
            if(extraInfoList.size()-(index+1) > 0) {
                addLineView();
            }
        }
    }

    private void addLineView() {
        View linearView = new View(this);
        linearView.setBackgroundColor(getResources().getColor(R.color.color_d8d8d8));
        LinearLayout.LayoutParams linearParas = new LinearLayout.LayoutParams(dip2px(0.5f),dip2px(26));
        linearParas.topMargin = dip2px(10);
        linearView.setLayoutParams(linearParas);
        mLinearExtraInfoView.addView(linearView);
    }

    private void addView(String info,int index) {
        SpannableString infoSs = new SpannableString(info);
        infoSs.setSpan(new StyleSpan(Typeface.BOLD),0,infoSs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(DigitUtil.ContainDigit(info)){
            String money = DigitUtil.getNumbers(info);
            int moneyStartIndex = DigitUtil.getNumbersStartIndex(info,money);
            //infoSs.setSpan(moneyColor,moneyStartIndex,moneyStartIndex+money.length()
            // ,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            infoSs.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843))
                    ,moneyStartIndex,infoSs.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        TextView itemView = new TextView(this);
        itemView.setText(infoSs);

        LinearLayout.LayoutParams params;
        if(extraInfoList.size() == 1) {
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    ,ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins((int)dip2px(22.0f),0,0,0);
        }else {
            params = new LinearLayout.LayoutParams(0
                    , ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
        }
        itemView.setGravity(Gravity.CENTER);
        itemView.setLayoutParams(params);
        mLinearExtraInfoView.addView(itemView);
    }

    //LinearLayout 动态加载View实现
/*    private void addView(String info,int index) {
        SpannableString infoSs = new SpannableString(info);
        infoSs.setSpan(new StyleSpan(Typeface.BOLD),0,infoSs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(DigitUtil.ContainDigit(info)){
            String money = DigitUtil.getNumbers(info);
            int moneyStartIndex = DigitUtil.getNumbersStartIndex(info,money);
            //infoSs.setSpan(moneyColor,moneyStartIndex,moneyStartIndex+money.length()
            // ,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            infoSs.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843))
                    ,moneyStartIndex,infoSs.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        TextView itemView = new TextView(this);
        itemView.setText(infoSs);

        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if(extraInfoList.size() == 1) {
            //一个信息
            params.setMargins(dip2px(22),dip2px(14),dip2px(0),dip2px(14));
        }else if(extraInfoList.size() == 2) {
            //二个信息
            if (index == 0) {
                params.setMargins(dip2px(39),dip2px(14),0,dip2px(14));
            }else if(index == 1) {
                addLineView(dip2px(38));
                params.setMargins(dip2px(48.5f),dip2px(14),0,dip2px(14));
            }

        }else if(extraInfoList.size() == 3) {
            //三个信息
            if(index == 0) {
                params.setMargins(dip2px(11),dip2px(14),0,dip2px(14));
            } else if (index == 1) {
                addLineView(dip2px(11));
                params.setMargins(dip2px(3.5f),dip2px(14),0,dip2px(14));
            } else if(index == 2) {
                addLineView(dip2px(4));
                params.setMargins(dip2px(20.5f),dip2px(14),0,dip2px(14));
            }

        }

        itemView.setLayoutParams(params);
        mLinearExtraInfoView.addView(itemView);

    }*/

    private void addLineView(int leftMargin){
        View linearView = new View(this);
        linearView.setBackgroundColor(getResources().getColor(R.color.color_d8d8d8));
        LinearLayout.LayoutParams linearParas = new LinearLayout.LayoutParams(dip2px(0.5f),dip2px(26));
        linearParas.topMargin = dip2px(10);
        linearParas.leftMargin = leftMargin;
        linearView.setLayoutParams(linearParas);
        mLinearExtraInfoView.addView(linearView);
    }

    public  int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
