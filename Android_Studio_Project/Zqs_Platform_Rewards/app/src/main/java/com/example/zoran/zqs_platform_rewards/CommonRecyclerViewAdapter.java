package com.example.zoran.zqs_platform_rewards;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by herr.wang on 2017/3/15.
 */
public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<CommonRecyclerViewAdapter.ItemViewHolder> {

    /**
     * for recyclerview, if your model used as an item in collection, implement it.
     */
    public static abstract class IItem implements Comparable<IItem>, IBaseItem {
        //use for sort
        protected int weight;

        @Override
        public int compareTo(IItem o) {
            if (weight > o.weight) {
                return -1;
            } else if (weight < o.weight) {
                return 1;
            }
            return 0;
        }

    }

    public interface IBaseItem {
        int getLayout();

        int getVariableId();
    }

    //prepared for child.
    protected List<? extends IBaseItem> list;
    protected Context context;

    public CommonRecyclerViewAdapter(List<? extends IBaseItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindTo(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return list != null ? list.get(position).getLayout() : 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding vdb;

        static ItemViewHolder create(ViewGroup parent, int viewType) {
            //返回值是null ，rootCause 类型不对应！
            /**
             * data标签：
             * 使用DataBinding编写布局，系统会自动生成一个继承ViewDataBinding类，
             * 而class属性可以指定这个类的名字，如果不指定，则会根据xml的名字自动生成。*/
            ViewDataBinding vdb = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
            Log.e("zqs","vdb = " + vdb);
            return new ItemViewHolder(vdb);
        }

        private ItemViewHolder(ViewDataBinding vdb) {
            super(vdb.getRoot());
            this.vdb = vdb;
        }

        void bindTo(IBaseItem item) {
            vdb.setVariable(item.getVariableId(), item);
            vdb.executePendingBindings();
        }
    }

    public void setData(List<? extends IBaseItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
