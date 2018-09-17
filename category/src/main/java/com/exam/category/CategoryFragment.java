package com.exam.category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exam.basecomponent.util.adapter.CommonAdapter;
import com.exam.basecomponent.util.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LowAndroider
 */
public class CategoryFragment extends Fragment {

    private ListView lvChannel;
    private CommonAdapter adapter;
    private List<Map<String,Object>> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category,container,false);
        lvChannel = view.findViewById(R.id.lv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
        initData();
        adapter = new CommonAdapter<Map<String,Object>>(getActivity(), android.R.layout.simple_list_item_1, data) {
            @Override
            public void convert(ViewHolder holder, Map<String,Object> o) {
                holder.setText(android.R.id.text1, (String) o.get("name"));
            }
        };
        lvChannel.setAdapter(adapter);
    }

    private void initData() {
        for(int i=0; i<5; i++) {
            Map<String,Object> model = new HashMap<>();
            model.put("name","CCTV" + (i+1));
            model.put("url","");
            data.add(model);
        }
    }
}
