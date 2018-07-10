package com.example.dida;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqs on 2018-7-9.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ItemViewHolder> {
    private List<String> list =new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context mContext;
    private ItemClickCallBack itemClickCallBack;
    private int SelectedRow;

    public FilterAdapter(List<String> list, Context context) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setItemClickCallBack(ItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_stroke, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_content.setText(list.get(position));
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.iv_select.setVisibility(View.VISIBLE);
                refresh(holder,position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(ItemViewHolder holder,int position) {
        itemClickCallBack.onItemClick(holder,position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rl_item;
        public TextView tv_content;
        public ImageView iv_select;

         public ItemViewHolder(View itemView) {
            super(itemView);
             rl_item = itemView.findViewById(R.id.rl_item);
             tv_content = itemView.findViewById(R.id.tv_content);
             iv_select = itemView.findViewById(R.id.iv_select);
        }
    }

    /**
     * 点击item事件回调给监听者
     * @author rander
     */
    public interface ItemClickCallBack
    {
        void onItemClick(ItemViewHolder holder,int selectedPosition);
    }
}
