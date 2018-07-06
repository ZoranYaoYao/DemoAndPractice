package com.zqs.dida;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    BidSuccessDialog bidSuccessDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, Object> map = new HashMap<>();
        // HTML 标签的文本 <b></b> 加粗
        //map.put(BidSuccessDialog.BID_MESSAGE, "凭借<b>橙星特权</b>");
        //map.put(BidSuccessDialog.DEFEAT_DRIVER_NUM, 146);
        if (bidSuccessDialog == null) {
            bidSuccessDialog = new BidSuccessDialog(this);
        }
        bidSuccessDialog.setData(map);
        bidSuccessDialog.show();

    }


}

