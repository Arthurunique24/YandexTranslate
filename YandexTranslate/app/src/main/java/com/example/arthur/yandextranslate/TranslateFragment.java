package com.example.arthur.yandextranslate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TranslateFragment extends Fragment {

    public static final String TAG = "TranslateFragmentTag";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MyTag", "TranslateFragment");
        return inflater.inflate(R.layout.activity_translate_fragment, null);
    }
}
