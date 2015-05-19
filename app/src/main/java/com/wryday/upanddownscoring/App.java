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

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        // Add-On Initialization
        LeakCanary.install(this);
        FlurryAgent.init(this, getFlurryApiKey());
    }

    public String getFlurryApiKey() {
        return BuildConfig.DEBUG ?
                getString(R.string.flurry_key_debug) :
                getString(R.string.flurry_key_production);
    }
}
