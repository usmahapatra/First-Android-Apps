package com.example.ujjwalsmahapatra.servicethreadsbasicpalle;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyService extends IntentService {
    public MyService() {
       super("b41");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //what you want to perform in a thread write here:
        Toast.makeText(this,"hiii",Toast.LENGTH_SHORT).show();
        Intent in = new Intent();
        in.setAction("ShowToast");
        sendBroadcast(in);
    }
}
