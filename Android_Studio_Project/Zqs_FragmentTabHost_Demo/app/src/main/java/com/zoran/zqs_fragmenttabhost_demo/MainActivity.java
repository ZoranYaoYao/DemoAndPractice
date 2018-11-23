package com.zoran.zqs_fragmenttabhost_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * refer : https://www.jianshu.com/p/a663803b2a44
 *
 * top, bottom 导航栏布局
 *  FragmentTabHost + ViewPager + Fragment
 *
 * 缺点：
 *  Tab与ViewPager 绑定太麻烦， 双向绑定，
 *  在5.0以后使用TabLayout进行布局， 进行单项绑定的
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private String textViewArray[] = { "首页", "分类"};
    private int imageViewArray[] = { R.drawable.tab_home_btn, R.drawable.tab_view_btn };

    private Class fragmentArray[] = {Fragment1.class, Fragment2.class };

    private List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initPage();//初始化页面
    }

    private void initPage() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();

        list.add(fragment1);
        list.add(fragment2);

        //绑定Fragment适配器
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        mTabHost.getTabWidget().setDividerDrawable(null);

    }

    private void initView() {
        vp = findViewById(R.id.pager);

        vp.addOnPageChangeListener(onPageChangeListener);
        layoutInflater = LayoutInflater.from(this);

        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);

        mTabHost.setOnTabChangedListener(onTabChangeListener);
        int count = textViewArray.length;
        for (int i=0; i<count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }

    }

    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }

    TabHost.OnTabChangeListener onTabChangeListener = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {
            int position = mTabHost.getCurrentTab();
            vp.setCurrentItem(position);
        }
    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            TabWidget widget = mTabHost.getTabWidget();
            int oldFocusability = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            mTabHost.setCurrentTab(i);
            widget.setDescendantFocusability(oldFocusability);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
