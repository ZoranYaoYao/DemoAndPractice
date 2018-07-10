package update2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dida.R;
import com.example.dida.UIHandler;

import java.util.ArrayList;

/**
 * Created by zqs on 2018-7-10.
 */

public class FilterFragment extends Fragment implements FilterAdapter.ItemClickCallBack {
    public static final String PARAM = "params";
    public static final String DEFAULT = "default";

    private View rootView;//缓存Fragment view
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FilterAdapter adapter;
    private View viewShadow;

    private ArrayList<String> list;
    private String defaultStr;

    public static FilterFragment newInstance(ArrayList<String> list,String str) {
        Bundle args = new Bundle();
        args.putSerializable(PARAM, list);
        args.putString(DEFAULT, str);
        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        list = (ArrayList<String>) bundle.getSerializable(PARAM);
        defaultStr = bundle.getString(DEFAULT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) parent.removeView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.frament_filter, container, false);
            recyclerView = rootView.findViewById(R.id.recyclerView);
            viewShadow = rootView.findViewById(R.id.view_shadow);
            viewShadow.setOnClickListener(shadowOnClickListener);
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new FilterAdapter(list, defaultStr, getContext());
            adapter.setItemClickCallBack(this);
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }

    @Override
    public void onItemClick(final FilterAdapter.ItemViewHolder holder, int selectedPosition) {
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
                ((ThirdActivity) getContext()).onItemClick(holder.tv_content.getText().toString());
            }
        }, 50);
    }

    private View.OnClickListener shadowOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            ((ThirdActivity) getContext()).onItemClick(null);
        }
    };
}
