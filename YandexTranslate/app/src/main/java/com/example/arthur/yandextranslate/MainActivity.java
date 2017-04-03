package com.example.arthur.yandextranslate;

import  android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private TranslateFragment translateFragment;
    private HistoryFragment historyFragment;
    private SettingsFragment settingsFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyTag", "onCreate");

        //Create FragmentManager
        fragmentManager = getSupportFragmentManager();

        //Initialization fragments
        translateFragment  = new TranslateFragment();
        historyFragment = new HistoryFragment();
        settingsFragment = new SettingsFragment();

        //Set TranslateFragment like basic
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, translateFragment, TranslateFragment.TAG);
        fragmentTransaction.commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_translate:
                    Log.d("MyTag", "navigation_translate");
                    if(fragmentManager.findFragmentByTag(TranslateFragment.TAG) == null) {
                        fragmentTransaction.add(R.id.container, translateFragment, TranslateFragment.TAG);
                        fragmentTransaction.commit();
                    }
                    if(fragmentManager.findFragmentByTag(TranslateFragment.TAG) != null){
                        fragmentTransaction.replace(R.id.container, translateFragment, TranslateFragment.TAG);
                        fragmentTransaction.commit();
                    }
                    return true;

                case R.id.navigation_history:
                    Log.d("MyTag", "navigation_history");
                    if(fragmentManager.findFragmentByTag(HistoryFragment.TAG) == null){
                        fragmentTransaction.add(R.id.container, historyFragment, HistoryFragment.TAG);
                        fragmentTransaction.commit();
                    }
                    if(fragmentManager.findFragmentByTag(HistoryFragment.TAG) != null){
                        fragmentTransaction.replace(R.id.container, historyFragment, HistoryFragment.TAG);
                        fragmentTransaction.commit();
                    }
                    return true;

                case R.id.navigation_settings:
                    Log.d("MyTag", "navigation_settings");
                    if(fragmentManager.findFragmentByTag(SettingsFragment.TAG) == null){
                        fragmentTransaction.add(R.id.container, settingsFragment, SettingsFragment.TAG);
                        fragmentTransaction.commit();
                    }
                    if(fragmentManager.findFragmentByTag(SettingsFragment.TAG) != null){
                        fragmentTransaction.replace(R.id.container, settingsFragment, SettingsFragment.TAG);
                        fragmentTransaction.commit();
                    }
                    return true;
            }

            return false;
        }

    };



}
