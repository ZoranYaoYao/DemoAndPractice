package com.example.zoran.zqs_databinding_study.conversions;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.databinding.ActivityCollectionsBinding;
import com.example.zoran.zqs_databinding_study.databinding.ActivityConversionsBinding;

/**
 * Created by zqs on 2018/2/6.
 * http://blog.csdn.net/u011315960/article/details/56671903?utm_source=itdadao&utm_medium=referral
 *
 * 1.自定义setter方法@BindingAdapte 运用
 *      (1) 引入自定义属性的命令空间
 *          xmlns:app="http://schemas.android.com/apk/res-auto"
 *      (2) xml文件中自定义属性
 *          app:layout_height="@{height}"
 *      (3) 书写转换器标签 @BindingAdapter(自定义属性) ，做映射关系
 *              @BindingAdapter("layout_height")
                public static void setLayoutHeight(View view,float height) {
 *     【场景】 1.方法里去重写xml里面的静态值
 *
 * 2.转换器 运用
 *       (1) 书写xml中的资源运用，判断是否需要转换器
 *                  android:background="@{isError.get() ? @color/red : @color/white}"
 *       (2) 对应的binding的java文件 写转换器方法
 *                  @BindingConversion
                    public static ColorDrawable convertColorToDrawable(int color) {
 */
public class ConversionsActivity extends BaseActivity {

    private ObservableBoolean mIsError = new ObservableBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConversionsBinding binding =
        DataBindingUtil.setContentView(this, R.layout.activity_conversions);

        binding.setHeight(400f);
        binding.setIsError(mIsError);
    }

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return  new ColorDrawable(color);
    }


    @BindingAdapter("layout_height")
    public static void setLayoutHeight(View view,float height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) height;
        view.setLayoutParams(params);
    }

    public void ToogleExchange(View view) {
        if(mIsError.get() == true) {
            mIsError.set(false);
        }else {
            mIsError.set(true);
        }
    }
}

/**
 * 1.  错误 ： app:layout_height="@{height}" 方法找不到
 *   / data binding error ****msg:Cannot find the setter for attribute 'app:layout_height' with parameter type float on android.view.View.
        file:D:\Project_WorkSpace\Android_Studio_Project\Zqs_DataBinding_Study\app\src\main\res\layout\activity_conversions.xml

    【Solution】 用转换器 映射对应方法！
        @BindingAdapter("layout_height")
        public static void setLayoutHeight(View view,float height) {


   2.
        D:\Project_WorkSpace\Android_Studio_Project\Zqs_DataBinding_Study\app\build\generated\source\apt\debug\com\example\zoran\zqs_databinding_study\databinding\ActivityConversionsBinding.java:158: 错误: 找不到符号
        isErrorMboundView1AndroidColorRedMboundView1AndroidColorWhite = ((isErrorGet) ? (getColorFromResource(mboundView1, R.color.red)) : (getColorFromResource(mboundView1, R.color.white)));
        ^
        符号:   变量 red
        位置: 类 color}


      【Solution】
        color.xml 没有对应的资源变量申明。
            区别系统变量 android:background="@android:color/black"
            自定义资源 @color/black
 * */