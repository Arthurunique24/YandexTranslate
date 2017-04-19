package com.example.arthur.yandextranslate;

import io.realm.RealmObject;

/**
 * Created by Arthur on 18.04.17.
 */

public class HistoryRealm extends RealmObject {
    private String realmTextToTranslate;
    private String realmTranslatedText;

    public void setRealmTextToTranslate(final String realmTextToTranslate){
        this.realmTextToTranslate = realmTextToTranslate;

    }

    public String getRealmTextToTranslate(){
        return realmTextToTranslate;
    }

    public void setRealmTranslatedText(final String realmTranslatedText){
        this.realmTextToTranslate = realmTranslatedText;
    }

    public String getRealmTranslatedText(){
        return realmTranslatedText;
    }
}
