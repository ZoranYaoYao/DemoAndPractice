package com.example.zoran.zqs_databinding_study.include;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.basic.User;
import com.example.zoran.zqs_databinding_study.databinding.ActivityIncludeBinding;

/**
 * Created by zqs on 2018/2/5.
 * 1.include传递绑定数据
 *    (1) layout根元素，申明命令空间
 *          xmlns:bind="http://schemas.android.com/apk/res-auto"
 *   （2） include标签中传递变量
 *          标准规范： bind:传递给下一个layout什么的变量name。
 *          bind:user="@{user}"/>
 * 2. 在java文件绑定数据对象
 *        标准规范：binding.set xml中的name（首字母大写）
 *        binding.setInclude_activity(this);
 * 3.绑定事件
 *      （1）创建一个监听器，并找一个实现
 *          class IncludeActivity extends BaseActivity implements SingleClickListener
 *      （2）在XML中申明监听的变量
 *           <variable
 *            name="listener"
 *            type="com.example.zoran.zqs_databinding_study.include.SingleClickListener"/>
 *      （3）onclick事件绑定对象
 *          android:onClick="@{listener.onClick}"/>
 */
public class IncludeActivity extends BaseActivity implements SingleClickListener{

     private ActivityIncludeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_include);
        binding.setListener(this);


        EditText editText = binding.layoutTextinput.etName;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                User user = new User(s.toString(), "Liang");
                binding.setUser(user);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View v) {
        Toast.makeText(this,"Clicked OK",Toast.LENGTH_SHORT).show();
    }
}
