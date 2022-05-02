package com.entitcs.crashloggenerator;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balsikandar.crashreporter.utils.CrashUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleyClassForUpload {
    private StringRequest stringRequest;
    private int workId = 0;
    private Context context;
    private RequestQueue requestQueue;

    public void uploadCrashFiles() {
        Log.e("TAG11", "uploadCrashFiles: " );
        try {
            final ArrayList<String> crashFiles = FetchCrashFile();
            getRequestQueue().cancelAll(this);
            stringRequest = new StringRequest(Request.Method.POST, "https://timekompas.com/api/user/uploadcrashfile",
                    response -> {
                        if (response != null) {
                            File fdelete = new File(crashFiles.get(0));
                            if (fdelete.exists()) {
                                fdelete.delete();
                                uploadCrashFiles();
                            }
                        }
                    },
                    error -> {
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("mobile_set", "test_shyam");
                    params.put("tkc_mob", "0123456789");
                    params.put("crash_file", getBase64FromPath(crashFiles.get(0)));
                    Log.e("TAG", "getParams: "+params.toString() );
                    return params;
                }
            };
            getRequestQueue().add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBase64FromPath(String path) {
        String base64 = "";
        try {/*from   w w w .  ja  va  2s  .  c om*/
            File file = new File(path);
            byte[] buffer = new byte[(int) file.length() + 100];
            @SuppressWarnings("resource")
            int length = new FileInputStream(file).read(buffer);
            base64 = Base64.encodeToString(buffer, 0, length,
                    Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }

    private ArrayList<String> FetchCrashFile() {
        ArrayList<String> filenames = new ArrayList<String>();
        File directory = new File(CrashUtil.getDefaultPath());
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            String file_name = files[i].getName();
            filenames.add(CrashUtil.getDefaultPath() + "/" + file_name);
        }
        return filenames;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        }
        return requestQueue;
    }

}
