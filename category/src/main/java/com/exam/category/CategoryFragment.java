package com.exam.category;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.exam.basecomponent.util.adapter.CommonAdapter;
import com.exam.basecomponent.util.adapter.ViewHolder;
import com.exam.category.dao.MovieDao;
import com.exam.category.entity.Movie;
import com.exam.category.live.LiveActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LowAndroider
 */
public class CategoryFragment extends Fragment implements MovieDao.PermissionCallback {

    private ListView lvChannel;
    private CommonAdapter<Movie> adapter;
    private List<Movie> data;
    private MovieDao movieDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category,container,false);
        lvChannel = view.findViewById(R.id.lv);
        movieDao = new MovieDao(getActivity());
        movieDao.setPermissionCallback(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
        adapter = new CommonAdapter<Movie>(getActivity(), android.R.layout.simple_list_item_1, data) {
            @Override
            public void convert(ViewHolder holder, Movie o) {
                holder.setText(android.R.id.text1, o.getDisplayName());
            }
        };
        lvChannel.setAdapter(adapter);

        lvChannel.setOnItemClickListener((parent, view1, position, id) -> {
            Movie movie = data.get(position);
            Intent intent = new Intent(getActivity(), LiveActivity.class);
            intent.putExtra("movie",movie);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if(movieDao.checkPermission()) {
                refresh();
            } else {
                movieDao.reqPermission(requireActivity());
            }

        } catch (IllegalStateException ignored) {
        }
    }

    private void refresh() {
        data = movieDao.getList();
        adapter.load(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPermissionGranted() {
        refresh();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        movieDao.onRequestPermissionsResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
