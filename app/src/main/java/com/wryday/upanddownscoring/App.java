package com.wryday.upanddownscoring;

import android.app.Application;
import android.content.Context;

import com.flurry.android.FlurryAgent;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    public static String getFlurryApiKey() {
        return BuildConfig.DEBUG ?
                sContext.getString(R.string.flurry_key_debug) :
                sContext.getString(R.string.flurry_key_production);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        FlurryAgent.init(this, App.getFlurryApiKey());
    }
}
