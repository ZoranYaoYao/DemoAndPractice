package com.example.zoran.linear.weight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  LinearLayou weight定义：
 *      自身width + 屏幕剩余dp中自己占有的百分比
 *      自身宽度 + 所占剩余空间的比例 = 真正的宽度
 *
 *  1.     <TextView
             android:layout_width="100dp"
             android:layout_height="wrap_content"
             android:layout_weight="1"
             android:text="Hello ONE!"/>

         <TextView
             android:layout_width="0dp"
             android:layout_height="wrap_content"
             android:layout_weight="1"
             android:text="Hello TWO!"/>

    【eg】 s4 1080*1920
    width = 100dp* 3+ （1080 - 100*3）*(1/2) = 690
 *
 * */
public class MainActivity extends AppCompatActivity {

    private TextView tv_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_one = findViewById(R.id.tv_one);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int width = tv_one.getWidth();
        //Toast 690
        Toast.makeText(this,"width = " + width,Toast.LENGTH_SHORT).show();
    }
}
