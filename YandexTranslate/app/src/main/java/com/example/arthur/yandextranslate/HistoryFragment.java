package com.example.arthur.yandextranslate;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

import io.realm.Realm;
import io.realm.RealmResults;

public class HistoryFragment extends Fragment {

    public static final String TAG = "HistoryFragmentTag";

    private List<History> historyList = new ArrayList<>();
    private RecyclerView recyclerView;

    private Realm realm;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MyTag", "HistoryFragment");
        View rootView = inflater.inflate(R.layout.activity_history_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        realm = Realm.getInstance(getContext());

        //Add histoy in hitoryFragment
        realm.beginTransaction();
        RealmResults<HistoryRealm> historyRealms = realm.where(HistoryRealm.class).findAll();
        if(!historyRealms.isEmpty()){
            for(int i = historyList.size(); i < historyRealms.size(); ++i){
                initializeData(historyRealms.get(i).getRealmTextToTranslate(), historyRealms.get(i).getRealmTranslatedText());
            }
        }
        else {
            historyList.clear();
            Toast.makeText(getActivity(), "Nothing in history :( ", Toast.LENGTH_LONG).show();
        }
        realm.commitTransaction();

        initializeAdapter();

        getActivity();
        return rootView;
        //return inflater.inflate(R.layout.activity_history_fragment, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initializeData(String textToTranslate, String translatedtext){
        historyList.add(new History(textToTranslate, translatedtext));
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void initializeAdapter(){
        RVAdapter rvAdapter = new RVAdapter(historyList);
        recyclerView.setAdapter(rvAdapter);
    }
}
