package com.example.dida;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 写到一个自定义控件中, 好扩展
 * 写一个itemView, 复用性高,
 */
public class MainActivity extends AppCompatActivity {


    private static final String[] strokeType = {"全部类型", "线上抢单", "推荐乘客"};
    private static final String[] strokeStatus = {"全部状态", "线上支付", "线下支付", "已失效"};

    private FilterView viewFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFilter = findViewById(R.id.view_filter);
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(1, new ArrayList<String>(Arrays.asList(strokeType)));
        map.put(2, new ArrayList<String>(Arrays.asList(strokeStatus)));
        viewFilter.setData(map);
        //        initView();
    }



    //    private void initView() {
    //        LayoutInflater inflater = LayoutInflater.from(this);
    //        View popContentView = inflater.inflate(R.layout.stroke_pop, null, false);
    //        typeRecyclerView = popContentView.findViewById(R.id.recyclerView);
    //        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    //        typeRecyclerView.setLayoutManager(layoutManager);
    //        typeAdapter.setData(typeList);
    //        typeRecyclerView.setAdapter(typeAdapter);
    //        typePopupWindow = new PopupWindow(popContentView);
    //    }

}


















