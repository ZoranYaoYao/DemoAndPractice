package com.example.zoran.zqs_databinding_study.dynamic;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.basic.User;
import com.example.zoran.zqs_databinding_study.databinding.AcitivityDynamicBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018/2/6.
 *
 *
 */

public class DynamicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AcitivityDynamicBinding binding =
            DataBindingUtil.setContentView(this, R.layout.acitivity_dynamic);
        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        UserAdapter userAdapter = new UserAdapter(getList());

        recyclerView.setAdapter(userAdapter);

    }

    private List<User> getList() {
         final int USER_COUNT = 10;
         List<User> userList = new ArrayList<>();
        for (int i = 0; i < USER_COUNT; i ++) {
            User user = new User(Randoms.nextFirstName(), Randoms.nextLastName());
            userList.add(user);
        }
        return userList;
    }
}
/**
 * 1. RecyclerView 的依赖关系
 *     android.support.v7.widget.
 * */
