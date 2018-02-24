package com.example.zoran.zqs_databinding_study.basic;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.databinding.ActivityBasicBinding;

/**
 * Created by zqs on 2018/2/5.
 *
 * XML绑定POJO相关操作
 *
 * -1. POJO 数据写法
 *          写对象时，变量为private，方法为public访问
 * 0. activity绑定binding和数据对象
 *          标准规范：(1)xml去掉下划线，首字母大写
 *                  (2)绑定对象，用set类名（）
 *          ActivityBasicBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_basic);
 *          binding.setUser(user);
 * 1. XML导包，引用静态方法
 *          <import type="android.view.View" />
 *    变量导包方法：
 *                   <import type="com.example.zoran.zqs_databinding_study.basic.User"/>
 *                   <variable name="user" type="User"/>
 * 2. 声明变量
 *          <variable name="user" type="User" />
 * 3. XML引用变量的属性和方法
 *          通过@{},加上属性名，或者方法名。 方法名不用加括号
 * 4. 合并运算符   标准规范：if（user.displayName ！= null?user.displayName:user.lastName）
 *       @{user.displayName ?? user.lastName}"
 * 5. 在java文件绑定数据对象
 *        标准规范：binding.set xml中的name（首字母大写）
 *         binding.setUser(user);
 */
public class BasicActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBasicBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_basic);
        User user = new User("Zhu","shanyao",22);
        binding.setUser(user);
    }
}
/**
 * 1. android:text="@{user.age}"/>   Error
 *    android:text="@{String.valueOf(user.age)}"/>
 *    由于DataBinding 只会按照参数列表的类型去查找 Data里面的方法，所以会去找setText(int)
 * */
