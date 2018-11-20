package com.assignment.search;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class SearchApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
