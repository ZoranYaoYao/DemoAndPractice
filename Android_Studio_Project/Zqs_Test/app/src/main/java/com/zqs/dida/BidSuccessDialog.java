package com.zqs.dida;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.zqs.dida.order.BidSuccessDialogBinding;

import java.util.Map;


/**
 * Created by zqs on 2018/5/10.
 */

public class BidSuccessDialog extends Dialog {
    public static final String BID_MESSAGE = "bid_message";
    public static final String DEFEAT_DRIVER_NUM = "defeatDriverNum";
    public static final String ORANGE_STAR_STATUS = "orange_star_status";
    public String bid_message;
    public int defeatDriverNum;
    public SpannableStringBuilder defeatDriverSsb;
    public int orange_star_status;

    TextView tvBeatNumber;
    TextView tvBidSuccessCopywriting;
    Context mContext;

    BidSuccessDialogBinding binding;


    public BidSuccessDialog(@NonNull Context context) {
        this(context,R.style.custom_dialog);  //default theme
    }

    public BidSuccessDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_bid_order_success,null,false);
        setContentView(binding.getRoot());
        tvBeatNumber = binding.tvBeatNumber;
        tvBidSuccessCopywriting = binding.tvBidSuccessCopywriting;

        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.color_00000099);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.width = DisplayUtil.dip2px(mContext,280);
            window.setAttributes(lp);
        }

        setCancelable(false);  //back key is unavailable;
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.setDialog(this);
        if(!TextUtils.isEmpty(bid_message)){
            tvBidSuccessCopywriting.setText(Html.fromHtml(bid_message));
        }

    }

    public void setData(Map<String,Object> map) {
        if(map !=null && map.size() != 0) {
            bid_message = (String) map.get(BID_MESSAGE);
            defeatDriverNum = (Integer) map.get(DEFEAT_DRIVER_NUM);
        }
        setDefaultData();
        orange_star_status = 0;
    }

    private void setDefaultData() {
        //if (TextUtils.isEmpty(bid_message))  bid_message = "本次抢单";
        if(defeatDriverNum <= 0) {
            defeatDriverSsb = new SpannableStringBuilder();
        } else {
            defeatDriverSsb = new SpanUtils().append("共击败了")
                    .append(String.valueOf(defeatDriverNum)).setForegroundColor(ContextCompat.getColor(mContext,R.color.color_e79c1e))
                    .append("位司机").create();
        }
    }

}
