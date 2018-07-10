package com.example.dida;

import android.content.Context;
import android.databinding.ObservableBoolean;

import com.example.dida.common.CommonRecyclerViewAdapter;

/**
 * Created by zqs on 2018-7-7.
 */
public class StrokeItem extends CommonRecyclerViewAdapter.IItem {

    public String content;
//    private ItemClickCallBack itemClickCallBack;
    public ObservableBoolean selected = new ObservableBoolean(false);

    public StrokeItem(String content) {
        this.content = content;
    }

    @Override
    public int getLayout() {
        return R.layout.item_stroke;
    }

    /**
     *  BR 相似 R.java文件
     *  item是xml 里面data的变量名字
     */
    @Override
    public int getVariableId() {
        return BR.item;
    }

    public void onClick(Context context) {
//        selected.set(true);
//        if(context instanceof ItemClickCallBack){
//            ((ItemClickCallBack) context).onItemClick(content);
//        }
    }

}
