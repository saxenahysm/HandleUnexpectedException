package com.entitcs.crashloggenerator;

import android.content.Intent;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.balsikandar.crashreporter.CrashReporter;

public class MyApplication extends MultiDexApplication {
    private static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG1111", "onCreate: MyApplication");
        context = this;
        CrashReporter.initialize(this);
        CrashReporter.disableNotification();
    }


    public static MyApplication getContext() {
        return context;
    }

}
