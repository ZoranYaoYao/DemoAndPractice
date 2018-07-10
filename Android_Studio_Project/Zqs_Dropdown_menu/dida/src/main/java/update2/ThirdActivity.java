package update2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.dida.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zqs on 2018-7-10.
 */

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] strokeType = {"全部类型", "线上抢单", "推荐乘客"};
    private static final String[] strokeStatus = {"全部状态", "线上支付", "线下支付", "已失效"};

    private TextView tvStrokeType;
    private TextView tvStrokeStatus;
    private TextView mSelectView;
    private Drawable rightDrawableSelected;
    private Drawable rightDrawableNoramal;
    FrameLayout fl_filter;
    private FilterFragment typeFragment;
    private FilterFragment statusFragment;

    private int mDisplayWidth;
    private Map<View, Fragment> source;
    private boolean shouldClose = false; //Popwindows显示时,应该关闭
    private int normalTextColor;
    private int selectTextColor;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_third);
        initView();
        initData();
    }

    private void initData() {
        normalTextColor = getResources().getColor(R.color.color_292d39);
        selectTextColor = getResources().getColor(R.color.color_F8B442);

        source = new HashMap<>();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(strokeType));
        typeFragment = FilterFragment.newInstance(list, "全部类型");
        source.put(tvStrokeType, typeFragment);

        ArrayList<String> listStatus = new ArrayList<>(Arrays.asList(strokeStatus));
        statusFragment = FilterFragment.newInstance(listStatus, "全部状态");
        source.put(tvStrokeStatus, statusFragment);
    }


    private void initView() {
        mDisplayWidth = getResources().getDisplayMetrics().widthPixels;
        rightDrawableSelected = getResources().getDrawable(R.drawable.icon_up_arrow);
        rightDrawableNoramal = getResources().getDrawable(R.drawable.icon_down_arrow);
        tvStrokeType = findViewById(R.id.tv_stroke_type);
        tvStrokeStatus = findViewById(R.id.tv_stroke_status);
        fl_filter = findViewById(R.id.fl_filter);

        fragmentManager = getSupportFragmentManager();
        tvStrokeType.setOnClickListener(this);
        tvStrokeStatus.setOnClickListener(this);
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


    public void onItemClick(String selected) {
        if (selected != null) {
            mSelectView.setText(selected);
        }
        changeSelectViewUI(false);
        fl_filter.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_stroke_type:
            case R.id.tv_stroke_status:
                updateFragment(v);
                break;
        }
    }

    private void updateFragment(View v) {
        if (mSelectView == v) {
            if (fl_filter.isShown()) {  //Core. 通过FrameLayout来显示隐藏
                changeSelectViewUI(false);
                fl_filter.setVisibility(View.GONE);
            } else {
                changeSelectViewUI(true);
                fragmentManager.beginTransaction().replace(R.id.fl_filter, source.get(v)).commit();
                fl_filter.setVisibility(View.VISIBLE);
            }
            return;
        }

        if (mSelectView != null) {
            changeSelectViewUI(false);
        }
        mSelectView = (TextView) v;
        changeSelectViewUI(true);
        fragmentManager.beginTransaction().replace(R.id.fl_filter, source.get(v)).commit();
        fl_filter.setVisibility(View.VISIBLE);

    }
}
