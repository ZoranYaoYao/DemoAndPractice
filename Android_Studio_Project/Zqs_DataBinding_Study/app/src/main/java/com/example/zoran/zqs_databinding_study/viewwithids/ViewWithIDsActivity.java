package com.example.zoran.zqs_databinding_study.viewwithids;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.databinding.ActivityViewwithidsBinding;

/**
 * Created by zqs on 2018/2/6.
 *
 * 1.View 对象在 Java代码中运用
 *     （1） 获取xml具体id控件对象
 *              binding.tvFirstName
 */

public class ViewWithIDsActivity extends BaseActivity {
    ActivityViewwithidsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_viewwithids);
    }

    public void onShowName(View view) {
        binding.tvFirstName.setText("Zhu");
        binding.tvLastName.setText("Shanyao");
    }
}
