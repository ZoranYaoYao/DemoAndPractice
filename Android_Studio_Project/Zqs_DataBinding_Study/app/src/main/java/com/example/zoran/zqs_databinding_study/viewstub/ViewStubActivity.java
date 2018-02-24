package com.example.zoran.zqs_databinding_study.viewstub;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.basic.User;
import com.example.zoran.zqs_databinding_study.databinding.ActivityViewstubBinding;
import com.example.zoran.zqs_databinding_study.databinding.ViewStubBinding;

/**
 * Created by zqs on 2018/2/6.
 *
 * 1.Viewstub动态加载 运用
 *     （1） xml中申明<viewstub 控件，并添加id 和布局
 *                      <ViewStub
                            android:id="@+id/viewstub"
                            android:layout="@layout/view_stub"/>

       （2） java代码中判断是否加载，并进行加载
                         if(!binding.viewstub.isInflated()){
                            //加载viewstub的方法
                            binding.viewstub.getViewStub().inflate();
       （3） 绑定stubview的binding，并进行传值
                         ViewStubBinding viewStubBinding =  DataBindingUtil.bind(inflated);
                         User user = new User("Zhu","Shanyao");
                         viewStubBinding.setUser(user);
 */
public class ViewStubActivity extends BaseActivity {

    ActivityViewstubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =
        DataBindingUtil.setContentView(this, R.layout.activity_viewstub);

        binding.viewstub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                //获取viewstub布局的binding
                ViewStubBinding viewStubBinding =  DataBindingUtil.bind(inflated);
                User user = new User("Zhu","Shanyao");
                viewStubBinding.setUser(user);
            }
        });

    }

    public void onInflateViewStub(View view) {
        if(!binding.viewstub.isInflated()){
            //加载viewstub的方法
            binding.viewstub.getViewStub().inflate();
        }
    }

}
