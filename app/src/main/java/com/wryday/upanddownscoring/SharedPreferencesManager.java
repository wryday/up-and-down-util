package com.wryday.upanddownscoring;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

public class SharedPreferencesManager {
    private static final String TAG = SharedPreferencesManager.class.getSimpleName();
    private static final String PREFERENCES_FILE = "up_and_down_scoring";


    private static SharedPreferencesManager mSharedPreferencesManager;
    private Context mContext;

    private SharedPreferences mSharedPreferences;

    public SharedPreferencesManager(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance(Context context) {
        if (mSharedPreferencesManager == null) {
            mSharedPreferencesManager = new SharedPreferencesManager(context);
        }

        return mSharedPreferencesManager;
    }


    public String getGameType() {
        return mSharedPreferences.getString(Constant.GAME_TYPE, Constant.GAME_TYPE_5_CARD);
    }

    public void setGameType(String gameType) {
        mSharedPreferences.edit().putString(Constant.GAME_TYPE, gameType).apply();
    }

    private void resetUserPrefs() {
        setGameType(null);
    }

    public void clearAllPrefs() {
        resetUserPrefs();
        mSharedPreferences.edit().clear().apply();

        // this clears all of the app's preferences...
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear com.wryday.upanddownscoring");
        } catch (IOException exception) {
            Log.e(TAG, "IOException " + exception.getMessage());
            exception.printStackTrace();
        }
    }

}
