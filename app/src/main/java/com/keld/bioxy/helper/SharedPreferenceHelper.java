package com.keld.bioxy.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceHelper {
    private static final String PREFS = "pref";
    private static final String ID = "id";
    private static SharedPreferenceHelper instance;
    private SharedPreferences prefs;

    private SharedPreferenceHelper(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceHelper getInstance(Context context){
        if (instance == null){
            instance = new SharedPreferenceHelper(context);
        }
        return instance;
    }

    public void saveAccessToken(String token){
        prefs.edit().putString(PREFS, token).apply();
    }

    public void saveUserId(int id){
        prefs.edit().putInt(ID, id).apply();
    }

    public void saveRefreshToken(String token){
        prefs.edit().putString(PREFS, token).apply();
    }

    public String getAccessToken(){
        return prefs.getString(PREFS, "");
    }

    public int getUserId(){
        return prefs.getInt(ID, 0);
    }

    public void clearPref() {
        prefs.edit().clear().apply();
    }
}
