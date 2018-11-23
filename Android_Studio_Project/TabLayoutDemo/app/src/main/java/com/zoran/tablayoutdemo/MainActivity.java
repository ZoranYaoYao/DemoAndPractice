package com.zoran.tablayoutdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * refer ：https://www.jianshu.com/p/ce1d060573ba
 * top，bottom导航栏整体布局
 *  TabLayout + ViewPager + Fragment
 *
 *  优点：
 *      可以自定义Tab的布局样式
 *      https://www.jianshu.com/p/1bfe615f1016
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); //隐藏掉整个ActionBar

        initViews();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);

        //设置Tab的图标
        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
        three.setIcon(R.mipmap.ic_launcher);
        four.setIcon(R.mipmap.ic_launcher);

    }
}
