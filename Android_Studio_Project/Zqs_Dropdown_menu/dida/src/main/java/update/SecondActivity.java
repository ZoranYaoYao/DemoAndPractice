package update;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dida.FilterAdapter;
import com.example.dida.R;
import com.example.dida.UIHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqs on 2018-7-10.
 */
public class SecondActivity extends AppCompatActivity implements FilterAdapter.ItemClickCallBack, View.OnClickListener {
    private static final String[] strokeType = {"全部类型", "线上抢单", "推荐乘客"};
    private static final String[] strokeStatus = {"全部状态", "线上支付", "线下支付", "已失效"};

    private TextView tvStrokeType;
    private TextView tvStrokeStatus;
    private TextView mSelectView;
    private View divideLine;
    private PopupWindow mPopupWindow;
    private Drawable rightDrawableSelected;
    private Drawable rightDrawableNoramal;

    /**
     * popupwindow展示的宽
     */
    private int mDisplayWidth;
    private Map<View, List<String>> source;
    private String selectedType;
    private String selectedStauts;
    private boolean shouldClose = false; //Popwindows显示时,应该关闭
    private int normalTextColor;
    private int selectTextColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();
        initPopView();
        initListener();
    }

    private void initView() {
        mDisplayWidth = getResources().getDisplayMetrics().widthPixels;
        rightDrawableSelected = getResources().getDrawable(R.drawable.icon_up_arrow);
        rightDrawableNoramal = getResources().getDrawable(R.drawable.icon_down_arrow);
        tvStrokeType = findViewById(R.id.tv_stroke_type);
        tvStrokeStatus = findViewById(R.id.tv_stroke_status);
        divideLine = findViewById(R.id.divide_line);
        mPopupWindow = new PopupWindow();
    }

    private void initData() {
        normalTextColor = getResources().getColor(R.color.color_292d39);
        selectTextColor = getResources().getColor(R.color.color_F8B442);

        source = new HashMap<>();
        List<String> list = new ArrayList<>(Arrays.asList(strokeType));
        source.put(tvStrokeType, list);
        List<String> listStatus = new ArrayList<>(Arrays.asList(strokeStatus));
        source.put(tvStrokeStatus, listStatus);
    }

    private void initListener() {
        tvStrokeType.setOnClickListener(this);
        tvStrokeStatus.setOnClickListener(this);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                shouldClose = true;
                changeSelectViewUI(false);
            }
        });
    }

    private void initPopView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mPopupWindow.setWidth(mDisplayWidth);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(true);

        for (Map.Entry<View, List<String>> item : source.entrySet()) {
            View popContentView = inflater.inflate(R.layout.stroke_pop, null, false);
            RecyclerView recyclerView = popContentView.findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            @SuppressWarnings("unchecked")
            FilterAdapter adapter = new FilterAdapter(item.getValue(), this);
            adapter.setItemClickCallBack(this);
            recyclerView.setAdapter(adapter);

            item.getKey().setTag(popContentView);
        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_stroke_type:
            case R.id.tv_stroke_status:
                updatePopWindow(v);
                break;
        }
    }

    /**
     * 显示,隐藏popupWindow
     */
    private void updatePopWindow(View view) {


        if (shouldClose && view == mSelectView) {
            shouldClose = false;
            return;
        }

        mSelectView = (TextView) view;
        if (mPopupWindow.isShowing()) {
            changeSelectViewUI(false);
            mPopupWindow.dismiss();
        } else {
            changeSelectViewUI(true);
            mPopupWindow.setContentView((View) mSelectView.getTag());
            mPopupWindow.showAsDropDown(divideLine, 0, 0);

        }
    }

    @Override
    public void onItemClick(final FilterAdapter.ItemViewHolder holder, int selectedPosition) {
        if (mPopupWindow != null) {
            RecyclerView recyclerView = ((View) mSelectView.getTag()).findViewById(R.id.recyclerView);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            for (int i = 0; i < manager.getItemCount(); i++) {
                if (i == selectedPosition) continue;

                View view = manager.getChildAt(i);
                FilterAdapter.ItemViewHolder Itemholder = (FilterAdapter.ItemViewHolder) recyclerView.getChildViewHolder(view);
                Itemholder.iv_select.setVisibility(View.GONE);
            }

            UIHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mSelectView == tvStrokeType) {
                        selectedType = holder.tv_content.getText().toString();
                        mSelectView.setText(selectedType);
                    } else {
                        selectedStauts = holder.tv_content.getText().toString();
                        mSelectView.setText(selectedStauts);
                    }
                    shouldClose = false;
                    changeSelectViewUI(false);
                    mPopupWindow.dismiss();
                }
            }, 50);

        }
    }


}
