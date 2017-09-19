package com.common;

import android.content.Context;

import org.litepal.LitePalApplication;

public class CommonApplication extends LitePalApplication  {
    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        LitePalApplication.initialize(this);
    }
}
