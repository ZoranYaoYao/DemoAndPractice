package com.example.dida;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zqs on 2018-7-7.
 */

public class FilterView2 extends LinearLayout implements View.OnClickListener, FilterAdapter.ItemClickCallBack {

    public static final int TYPE = 1;
    public static final int STATUS = 2;

    private Context mContext;
    private TextView tvStrokeType;
    private TextView tvStrokeStatus;
    private TextView mSelectView;
    private View divideLine;
    /**
     * 正显示的popupwindows
     */
    private PopupWindow mShowPopupWindow;
    /**
     * popupwindow展示的宽
     */
    private int mDisplayWidth;
    private List<StrokeItem> typeList = new ArrayList<>();
    private List<StrokeItem> statusList = new ArrayList<>();
    private Map<View, PopupWindow> map = new HashMap<>();
    private Map<PopupWindow, List<StrokeItem>> popMap = new HashMap<>();
    private int normalTextColor = getResources().getColor(R.color.color_292d39);
    private int selectTextColor = getResources().getColor(R.color.color_F8B442);
    private Drawable rightDrawableSelected;
    private Drawable rightDrawableNoramal;
    private boolean isClickOutofPop = false; //记录是否点击Popwindows外部区域
    private String filterString;

    public FilterView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mDisplayWidth = getResources().getDisplayMetrics().widthPixels;
        rightDrawableSelected = getResources().getDrawable(R.drawable.icon_up_arrow);
        rightDrawableNoramal = getResources().getDrawable(R.drawable.icon_down_arrow);
        initView();
    }

    private void initListener() {
        tvStrokeType.setOnClickListener(this);
        tvStrokeStatus.setOnClickListener(this);

        for (Map.Entry item : map.entrySet()) {
            ((PopupWindow) item.getValue()).setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    isClickOutofPop = true;
                    changeSelectViewUI(false);
                    mShowPopupWindow.dismiss();
                }
            });
        }

    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_filter_view, null, false);
        addView(view);
        tvStrokeType = view.findViewById(R.id.tv_stroke_type);
        tvStrokeStatus = view.findViewById(R.id.tv_stroke_status);
        divideLine = view.findViewById(R.id.divide_line);
    }

    public void setData(Map<Integer, List<String>> map) {
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            int num = (int) entry.getKey();
            switch (num) {
                case TYPE:
                    setData(tvStrokeType, typeList, entry.getValue());
                    break;
                case STATUS:
                    setData(tvStrokeStatus, statusList, entry.getValue());
                    break;
            }
        }
        initPopView();
        initListener();
    }


    View view1;View view2;
    private void initPopView() {
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        for (Map.Entry<View, PopupWindow> item : map.entrySet()) {
//            View relatedView = item.getKey();
//            PopupWindow popupWindow = (PopupWindow) relatedView.getTag();
//            View popContentView = inflater.inflate(R.layout.stroke_pop, null, false);
//            RecyclerView recyclerView = popContentView.findViewById(R.id.recyclerView);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//            recyclerView.setLayoutManager(layoutManager);
//            @SuppressWarnings("unchecked")
//            FilterAdapter adapter = new FilterAdapter(popMap.get(popupWindow), mContext);
//            adapter.setItemClickCallBack(this);
//            recyclerView.setAdapter(adapter);
//            relatedView.setTag(R.id.tag_first,recyclerView);
//
//            if(view1 == null) {
//                view1 = popContentView;
//            }else {
//                view2 = popContentView;
//            }
//
//            popupWindow.setContentView(popContentView);
//            popupWindow.setWidth(mDisplayWidth);
//            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
//            popupWindow.setFocusable(false);
//            popupWindow.setOutsideTouchable(true);
//
//        }

    }

    private void setData(View view, List<StrokeItem> wrapperList, List<String> list) {
        for (String str : list) {
            StrokeItem item = new StrokeItem(str);
            wrapperList.add(item);
        }
        PopupWindow popupWindow = new PopupWindow();
        view.setTag(popupWindow);
        map.put(view, popupWindow);
        popMap.put(popupWindow, wrapperList);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_stroke_type:
            case R.id.tv_stroke_status:
                Log.e("zqs1", "onClick");
                popupWindow = new PopupWindow(this);
                popupWindow.setWidth(mDisplayWidth);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(true);
                updatePopWindow(v);
                break;
        }
    }

    /**
     * 显示,隐藏popupWindow
     */
    PopupWindow popupWindow;int i = 1;
    private void updatePopWindow(View view) {
        if(i % 2 == 0) {
            popupWindow.setContentView(view1);
        } else {
            popupWindow.setContentView(view2);
        }
        i++;
        popupWindow.showAsDropDown(divideLine, 0, 0);


//        if (view == mSelectView && isClickOutofPop) {
//            changeSelectViewUI(false);//点击同一过滤类型View
//            isClickOutofPop = false;
//            return;
//        }
//
//        if (view != mSelectView && mShowPopupWindow != null && mShowPopupWindow.isShowing()) {
//            mShowPopupWindow.dismiss();  //dismiss上一次的popwindow.
//        }
//
//        mSelectView = (TextView) view;
//        mShowPopupWindow = (PopupWindow) mSelectView.getTag();
//        if (mShowPopupWindow != null && !mShowPopupWindow.isShowing()) {
//            changeSelectViewUI(true);
//            mShowPopupWindow.showAsDropDown(divideLine, 0, 0);
//        }

    }

    public void changeSelectViewUI(boolean isSelected) {
        if (isSelected) {
            mSelectView.setTextColor(selectTextColor);
            rightDrawableSelected.setBounds(0, 0, rightDrawableSelected.getMinimumWidth(), rightDrawableSelected.getMinimumHeight());
            mSelectView.setCompoundDrawables(null, null, rightDrawableSelected, null);
        } else {
            mSelectView.setTextColor(normalTextColor);
            rightDrawableNoramal.setBounds(0, 0, rightDrawableNoramal.getMinimumWidth(), rightDrawableNoramal.getMinimumHeight());
            mSelectView.setCompoundDrawables(null, null, rightDrawableNoramal, null);
        }
    }




    @Override
    public void onItemClick(final FilterAdapter.ItemViewHolder holder, int selectedPosition) {
        if (mShowPopupWindow != null) {
            RecyclerView recyclerView = (RecyclerView) mSelectView.getTag(R.id.tag_first);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            for(int i =0; i < manager.getItemCount();i++) {
                if(i == selectedPosition) continue;

                View view = manager.getChildAt(i);
                FilterAdapter.ItemViewHolder Itemholder = (FilterAdapter.ItemViewHolder) recyclerView.getChildViewHolder(view);
                Itemholder.iv_select.setVisibility(View.GONE);
            }

            UIHandler.post(new Runnable() {
                @Override
                public void run() {
                    filterString = holder.tv_content.getText().toString();
                    mSelectView.setText(filterString);
                    isClickOutofPop = false;
                    changeSelectViewUI(false);
                    mShowPopupWindow.dismiss();
                }
            }, 100);

        }
    }
}
