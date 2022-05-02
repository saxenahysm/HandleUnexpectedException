package com.entitcs.crashloggenerator;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceToSendBackgroundCrashReportFile  extends IntentService {
    public ServiceToSendBackgroundCrashReportFile(String name) {
        super("ServiceToSendBackgroundCrashReportFile_\t"+name);
    }
    public ServiceToSendBackgroundCrashReportFile(){
        super("ServiceToSendBackgroundCrashReportFile");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("TAG11", "onHandleIntent: " );
        new VolleyClassForUpload().uploadCrashFiles();
    }
}
