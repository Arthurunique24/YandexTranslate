package com.example.arthur.yandextranslate;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "SettingsFragmentTag";

    Button btnClearHistory;

    private Realm realm;
    RealmResults<HistoryRealm> historyRealms;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("MyTag", "TranslateFragment");
        View rootView = inflater.inflate(R.layout.activity_settings_fragment, container, false);

        btnClearHistory = (Button) rootView.findViewById(R.id.btnClearHistory);
        btnClearHistory.setOnClickListener(this);

        realm = Realm.getInstance(getContext());
        historyRealms = realm.where(HistoryRealm.class).findAll();

        getActivity();
        return rootView;
        //return inflater.inflate(R.layout.activity_settings_fragment, null);
    }

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnClearHistory:
                realm.beginTransaction();
                if (!historyRealms.isEmpty()) {
//                    for (int i = historyRealms.size(); i > 0; --i) {
//                        historyRealms.get(i).removeFromRealm();
//                    }
                    historyRealms.clear();
                }
                realm.commitTransaction();
                Toast.makeText(getActivity(), "History cleared", Toast.LENGTH_LONG).show();
                break;
        }
    }

}
