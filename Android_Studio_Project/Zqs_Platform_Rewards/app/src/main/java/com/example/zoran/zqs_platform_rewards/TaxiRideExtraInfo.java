package com.example.zoran.zqs_platform_rewards;

import com.example.zoran.zqs_platform_rewards.BR;
/**
 * Created by Zoran on 2018/1/26.
 */
public class TaxiRideExtraInfo extends CommonRecyclerViewAdapter.IItem{
    //type-item 1 means 带有钱信息
    public static final int ITEM_VIEW_TYPE_ITEM_FIRST = 1;
    //type-item 2 means 打表来接
    public static final int ITEM_VIEW_TYPE_ITEM_SECOND = 2;

    //打表来接
    public int pick_by_meter;
    //平台奖励(单位：分)
    public int platform_subsidy_cent;

    //调度费 (zqs:类型转换为题!)
    public int extra_fee;

    // data binding 字段必须是public。不然编译访问不到
    public String PrefixOfPrice;
    public String StringInfo;
    public int LayoutType;

    public int getLayoutType() {
        return 0;
    }

    @Override
    public int getLayout() {
        if (platform_subsidy_cent > 0 || extra_fee > 0) {
            //一个item中互斥
            PrefixOfPrice = platform_subsidy_cent > 0 ? "平台奖励" : "调度费";
            return R.layout.gridview_extra_info_item_first;
        }else if(pick_by_meter == 1) {
            StringInfo = "打表来接";
            return R.layout.gridview_extra_info_item_second;
        }

        return 0;
    }

    @Override
    public int getVariableId() {
        return BR.item;
    }
}
