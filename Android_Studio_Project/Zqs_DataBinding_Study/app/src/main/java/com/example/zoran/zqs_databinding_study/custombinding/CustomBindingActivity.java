package com.example.zoran.zqs_databinding_study.custombinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.ContractBinding;
import com.example.zoran.zqs_databinding_study.R;

/**
 * Created by zqs on 2018/2/5.
 * 1.自定义Binding对象：
 *      (1) 在xml中的data标签中申明 class属性值
 *      (2) 在activity中绑定xml文件
 *                  ContractBinding binding = DataBindingUtil
 *                   .setContentView(this, R.layout.activity_customdatabinding);
 * 2. 好处？
 */
public class CustomBindingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContractBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_customdatabinding);
        Contact contact = new Contact("188555","China");
        binding.setContact(contact);

    }
}
