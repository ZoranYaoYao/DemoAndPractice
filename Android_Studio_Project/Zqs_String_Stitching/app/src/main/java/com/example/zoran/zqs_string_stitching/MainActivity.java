package com.example.zoran.zqs_string_stitching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 0.字符串格式化  rebase
 *      http://blog.csdn.net/kingoneyun/article/details/70237305
 *          2.属性值举例说明
 *               %n$ms：代表输出的是字符串，n代表是第几个参数，设置m的值可以在输出之前放置空格
 *               %n$md：代表输出的是整数，n代表是第几个参数，设置m的值可以在输出之前放置空格，也可以设为0m,在输出之前放置m个0
 *               %n$mf：代表输出的是浮点数，n代表是第几个参数，设置m的值可以控制小数位数，如m=2.2时，输出格式为00.00 （会四舍五入）
 *                      1.m的整数位，控制空格数 = m - 数值位数 ( >0 才显示！)
 *                      2.m的小数位数，控制单精度的小数位数
 *                      3.m的小数位数，会进行四舍五入， eg 15.589f m=2.2 => result:15.59
 *              【实例】
 *               %n$m:： 按照分进行金额展示
 *                       <string name="plateform_subsidy_cent">平台奖励%1$2.2f元</string>
 *                       float money = cent/100f m=2.2
 *               %n$md:
 *
 * 1.快速生成string .xml
 *      https://www.cnblogs.com/H-BolinBlog/p/5971826.html
 * 2.字符串拼接
 *      http://blog.csdn.net/muyi_amen/article/details/48027787
 * 3.单精度float 保留2位小数
 *      http://blog.csdn.net/qq_25943493/article/details/51781542
 */
public class MainActivity extends AppCompatActivity {
    private TextView mtvStitching;
    private TextView mtvStitchingFloat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtvStitching = findViewById(R.id.tv_stitching);
        mtvStitchingFloat = findViewById(R.id.tv_stitching_two);

        //1.
        mtvStitching.setText(R.string.test_alt_enter);

        int rewards = 115;
        float rewards_float = 56/100f;
        //2.
        String str_int = getResources().getString(R.string.plateform_subsidy_int,rewards);
        mtvStitching.setText(str_int);
        String str_float = getResources().getString(R.string.plateform_subsidy_float,rewards_float);
        mtvStitchingFloat.setText(str_float);


    }
}
/**
 *  String.format调用堆栈
 *
 E/AndroidRuntime( 7572): Caused by: java.util.MissingFormatWidthException: -f
 E/AndroidRuntime( 7572):        at java.util.Formatter$FormatToken.checkFlags(Formatter.java:1365)
 E/AndroidRuntime( 7572):        at java.util.Formatter.transform(Formatter.java:1440)
 E/AndroidRuntime( 7572):        at java.util.Formatter.doFormat(Formatter.java:1079)
 E/AndroidRuntime( 7572):        at java.util.Formatter.format(Formatter.java:1040)
 E/AndroidRuntime( 7572):        at java.util.Formatter.format(Formatter.java:1009)
 E/AndroidRuntime( 7572):        at java.lang.String.format(String.java:1998)
 E/AndroidRuntime( 7572):        at android.content.res.Resources.getString(Resources.java:355)
 E/AndroidRuntime( 7572):        at com.example.zoran.zqs_string_stitching.MainActivity.onCreate(MainActivity.java:43)
 E/AndroidRuntime( 7572):        at android.app.Activity.performCreate(Activity.java:5206)
 E/AndroidRuntime( 7572):        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1094)
 E/AndroidRuntime( 7572):        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2074)

 *<!--平台奖励-->
 *<string name="plateform_subsidy_cent">平台奖励%1$2.2f元</string>
 *<string name="dispatch_fee_yuan">调度费%1$1d元</string>
 *
 * */
