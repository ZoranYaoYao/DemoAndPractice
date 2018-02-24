package com.example.zoran.zqs_databinding_study.resource;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.databinding.ActivityResourceBinding;

/**
 * Created by zqs on 2018/2/5.
 *
 * 1.XML引用资源文件
 *     （1）引用资源文件格式： @{@xxx/yyy}
 *      [eg] @{@string/firstname}  @{@string/nameFormat(firsName,lastName)}
 *
 * 2.引用demins资源文件
 *      @dimen/largePadding
 * 3.引用strings资源文件
 *      @{@string/nameFormat(firsName,lastName)}
 * 4.引用复数资源文件
 *      @{@plurals/banana(bananaCount)}
 */

public class ResourceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityResourceBinding binding = DataBindingUtil.setContentView(this
                , R.layout.activity_resource);
        binding.setLarge(false);
        binding.setFirsName("Zhu");
        binding.setLastName("Shanyao");
        binding.setBananaCount(50);
        binding.setOrangeCount(0);
    }
}
