package com.example.arthur.yandextranslate;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    public static final String TAG = "HistoryFragmentTag";

    private List<History> historyList;
    private RecyclerView recyclerView;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MyTag", "HistoryFragment");
        View rootView = inflater.inflate(R.layout.activity_history_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        getActivity();
        return rootView;
        //return inflater.inflate(R.layout.activity_history_fragment, null);
    }

    private void initializeData(){
        historyList = new ArrayList<>();
        historyList.add(new History("Hello", "Привет"));
        historyList.add(new History("kek", "lol"));
        historyList.add(new History("Arthur", "Vika"));
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void initializeAdapter(){
        RVAdapter rvAdapter = new RVAdapter(historyList);
        recyclerView.setAdapter(rvAdapter);
    }
}
