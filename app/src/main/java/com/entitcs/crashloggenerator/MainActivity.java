package com.entitcs.crashloggenerator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.balsikandar.crashreporter.utils.CrashUtil;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> filenames = new ArrayList<String>();
        File directory = new File(CrashUtil.getDefaultPath());
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++){
            String file_name = files[i].getName();
            filenames.add(file_name);
        }
        Log.e("TAG11", "onCreate: "+filenames );
        if(!filenames.isEmpty()){
            startService(new Intent(this, ServiceToSendBackgroundCrashReportFile.class));
        }
    }

    public void onClick(View view) {
        Log.e("TAG", "onCreate: " + arrayList.size());
        //startActivity(new Intent(MainActivity.this, SendLog.class));
    }
}