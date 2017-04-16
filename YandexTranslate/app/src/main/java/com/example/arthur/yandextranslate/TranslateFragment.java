package com.example.arthur.yandextranslate;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

public class TranslateFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "TranslateFragmentTag";
    private static int i = 0;

    EditText editTextTranslate;
    Button btnTranslate;
    TextView tvTranslatedText, tvTextToTranslate;

    String[] langOne = {"ru", "en", "de", "fr", "ja"};
    String[] langTwo = {"ru", "en", "de", "fr", "ja"};

    Spinner spinner1;
    Spinner spinner2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MyTag", "TranslateFragment");
        View rootView = inflater.inflate(R.layout.activity_translate_fragment, container, false);

        editTextTranslate = (EditText) rootView.findViewById(R.id.editTextTranslate);
        btnTranslate = (Button) rootView.findViewById(R.id.btnTranslate);
        btnTranslate.setOnClickListener(this);
        tvTranslatedText = (TextView) rootView.findViewById(R.id.tvTranslatedText);
        tvTextToTranslate = (TextView) rootView.findViewById(R.id.tvTextToTranslate);

        spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
        spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);

        ArrayAdapter<String> arrayAdapter1 =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, langOne);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> arrayAdapter2 =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, langTwo);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);

        getActivity();
        return rootView;
        //return inflater.inflate(R.layout.activity_translate_fragment, null);
    }

    private class TranslateText extends AsyncTask<String, String, String> {
        private String translatedText;

        @Override
        protected String doInBackground(String... urls) {
            Log.d("MyTag", "doInBackground");
            String lang = urls[0];
            Log.d("MyTag", urls[0]);
            String input = urls[1];
            Log.d("MyTag", urls[1]);
            String translatedTextInBackground = null;
            try {
                translatedTextInBackground = translate(lang, input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("MyTag", translatedTextInBackground);

            translatedText = translatedTextInBackground;

            return translatedTextInBackground;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvTranslatedText.setText(translatedText);
        }
    }

    private static String translate(String lang,  String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170331T115220Z.3987a8b36f2c7b04.7eb1b41bfc32a9536510568f7db47d0d9437f49c";
        URL urlObj = new URL(urlStr);

        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        i++;
        /*if (translated.equals(input) && i < 2) {
            // if return equal of entered text - we need change direction of translation
            return translate("en", input);
        } else */return translated;
    }

    @Override
    public void onClick(View view) {
        String textToTranslate = editTextTranslate.getText().toString();

        switch (view.getId()){
            case R.id.btnTranslate:
                Log.d("MyTag", "btnTranslate");
                if(!Objects.equals(editTextTranslate.getText().toString(), "")) {
                    TranslateText translateText;
                    translateText = new TranslateText();
                    //translateText.execute("en-vi", textToTranslate);

                    String langToTranslate = spinner1.getSelectedItem().toString();
                    String langTranslated = spinner2.getSelectedItem().toString();
                    translateText.execute(langToTranslate + "-" + langTranslated, textToTranslate);

                    tvTextToTranslate.setText(editTextTranslate.getText().toString());
                    editTextTranslate.setText("");
                }
                else {
                    Toast.makeText(getActivity(), "Incorrect text", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
