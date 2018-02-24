package com.example.zoran.zqs_platform_rewards;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

//http://blog.csdn.net/qq_34589749/article/details/53482659
public class MainActivity extends AppCompatActivity {

    private RecyclerView mExtraInfoView;
    private CommonRecyclerViewAdapter mExraInfoAdapter;

    private RelativeLayout mTaxiRideExtraInfoView;

    private List<TaxiRideExtraInfo> mDataList;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定activity -ActivityMainBinding 是xml文件名去掉"_"的驼峰命名
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mExtraInfoView = activityMainBinding.recycleExtraInfo;
        setdata();
        setupExtraInfo();
        TextView item = activityMainBinding.tvItem;
        SpannableString str = new SpannableString("调度费100元");
        str.setSpan(new StyleSpan(Typeface.BOLD),0,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843)),3,6,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //str.setSpan(new StyleSpan(Typeface.BOLD),6,7,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        item.setText(str);

        //概念图样式
        mTaxiRideExtraInfoView = activityMainBinding.reExtraInfo;
        setExtraInfoOrder();
        setupTaxiRideExtraInfo();

        //Second
        mExtraInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    TaxiRideExtraInfo data1;
    ArrayList<String> extraInfoList;

    private void setExtraInfoOrder() {
        //默认顺序 调度费，平台奖励，打表来接，
        data1= new TaxiRideExtraInfo();
        data1.extra_fee = 100;
        data1.pick_by_meter = 1;
        data1.platform_subsidy_cent = 155;
        extraInfoList = new ArrayList<>();
        if (data1.extra_fee > 0) extraInfoList.add("调度费用"+data1.extra_fee+"元");
        if (data1.pick_by_meter == 1 ) extraInfoList.add("打表来接");
        float rewards = data1.platform_subsidy_cent/100.0f;
        if(data1.platform_subsidy_cent > 0) extraInfoList.add("平台奖励"+rewards+"元");
    }

    //Relativelayout 动态加载View实现
    private void addView(String info,int index) {
        SpannableString infoSs = new SpannableString(info);
        infoSs.setSpan(new StyleSpan(Typeface.BOLD),0,infoSs.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(DigitUtil.ContainDigit(info)){
            String money = DigitUtil.getNumbers(info);
            int moneyStartIndex = DigitUtil.getNumbersStartIndex(info,money);
            //infoSs.setSpan(moneyColor,moneyStartIndex,moneyStartIndex+money.length()
            // ,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            infoSs.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843))
                    ,moneyStartIndex,infoSs.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        TextView itemView = new TextView(this);
        itemView.setId(index + 100);
        itemView.setText(infoSs);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16,16,16,0);
        if(index == 0) {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }else if(index == 1) {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        } else {
            //于上上个view对齐
            if(index%2 == 0) {
                //对齐左侧
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.BELOW,mTaxiRideExtraInfoView.getChildAt(index-2).getId());
            }else {
                //对齐右侧
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.BELOW,mTaxiRideExtraInfoView.getChildAt(index-2).getId());
            }

        }
        itemView.setPadding(16,16,16,16);
        itemView.setBackground(getResources().getDrawable(R.drawable.bg_stroke_xx));
        itemView.setLayoutParams(params);
        mTaxiRideExtraInfoView.addView(itemView);

    }

    private void setupTaxiRideExtraInfo() {

        if(data1 == null){
            mTaxiRideExtraInfoView.setVisibility(View.GONE);
            return;
        }
        int rowCount;
        if(extraInfoList.size()%2 == 0) {
            rowCount = extraInfoList.size()/2;
        }else {
            rowCount = extraInfoList.size()/2 +1;
        }

        for (int index = 0; index < extraInfoList.size(); index++) {
            addView(extraInfoList.get(index),index);
        }


//        //具体分析
//        LinearLayout linearRowOne = new LinearLayout(this);
//        LinearLayout.LayoutParams params = new
//                LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        //调度费用
//        if (data1.extra_fee > 0) {
//            addView(linearRowOne,"调度费",100,Align.LEFT);
//        }
//        //打表来接
//        if(data1.pick_by_meter == 1 ) {
//            if(linearRowOne.getChildCount()%2 != 0) {
//                addView(linearRowOne,"打表来接",Align.RIGHT);
//            }else {
//                addView(linearRowOne,"打表来接",Align.LEFT);
//            }
//        }
//        //平台奖励
//        LinearLayout linearRowTwo = null;
//        if(data1.platform_subsidy_cent > 0) {
//            if(linearRowOne.getChildCount() != 2) {
//                if(linearRowOne.getChildCount()%2 != 0) {
//                    addView(linearRowOne,"调度费",150,Align.RIGHT);
//                }
//                addView(linearRowOne,"调度费",150,Align.LEFT);
//            }else {
//                linearRowTwo = new LinearLayout(this);
//                LinearLayout.LayoutParams paramsRowTwo = new
//                        LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT);
//                addView(linearRowTwo,"调度费",150,Align.LEFT);
//            }
//
//
//        }
//
//        mTaxiRideExtraInfoView.addView(linearRowOne);
//        if(linearRowTwo != null) mTaxiRideExtraInfoView.addView(linearRowTwo);

    }

    enum Align {
        LEFT,RIGHT
    }

    private void addView(LinearLayout parent,String prefix,int money,Enum<Align> align) {
        //LayoutInflater.from(this).inflate(R.layout.gridview_extra_info_item_first)
        String yuan = "元";
        String info = prefix + String.valueOf(money) + yuan;
        SpannableString infoSs = new SpannableString(info);
        infoSs.setSpan(new StyleSpan(Typeface.BOLD),0,infoSs.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        int moneyColor = getResources().getColor(R.color.color_ffb843);
        infoSs.setSpan(moneyColor,prefix.length()-1,info.indexOf(yuan)+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView itemView = new TextView(this);
        itemView.setText(infoSs);
        if(align == Align.RIGHT) {
            itemView.setGravity(Gravity.END);
        }
        parent.addView(itemView);
    }

    private void addView(LinearLayout parent,String info,Enum<Align> align) {
        SpannableString infoSs = new SpannableString(info);
        infoSs.setSpan(new StyleSpan(Typeface.BOLD),0,infoSs.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView itemView = new TextView(this);
        itemView.setText(infoSs);
        if(align == Align.RIGHT) {
            itemView.setGravity(Gravity.END);
        }
        parent.addView(itemView);
    }


    private void setdata() {
        TaxiRideExtraInfo data1= new TaxiRideExtraInfo();
        data1.extra_fee = 100;
        data1.LayoutType = 1;

        TaxiRideExtraInfo data2= new TaxiRideExtraInfo();
        data2.pick_by_meter = 1;

        TaxiRideExtraInfo data3= new TaxiRideExtraInfo();
        data3.platform_subsidy_cent = 100;

        mDataList = new ArrayList<>();
        mDataList.add(data1);
        mDataList.add(data2);
        mDataList.add(data3);

    }

    private void setupExtraInfo() {
        //zqs 不设置LayoutManager 就不会走Adapter的 createViewHolder,所以是recycleView的必须方法
        // ListView 实现
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mExtraInfoView.setLayoutManager(layoutManager);

        // 如果我们想要一个GridView形式的RecyclerView，那么在LayoutManager上我们就要使用GridLayoutManager
        // 实例化一个GridLayoutManager，列数为3
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mExtraInfoView.setLayoutManager(layoutManager);

        mExraInfoAdapter = new CommonRecyclerViewAdapter(mDataList,this);
        mExtraInfoView.setAdapter(mExraInfoAdapter);
    }
}