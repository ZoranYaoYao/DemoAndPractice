package com.example.zoran.zqs_databinding_study.dynamic;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.basic.User;
import com.example.zoran.zqs_databinding_study.databinding.RecycleItemUserBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018/2/6.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;

    public UserAdapter(List list) {
        userList = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_user,parent,false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    /**
     *  ？？
     * */
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList != null?userList.size():0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private RecycleItemUserBinding mBinding; //bind 用于绑定xml的, 并且通过mBinding 获取xml里面的id对象

        public UserViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(User user){
            mBinding.setUser(user);
        }
    }
}
