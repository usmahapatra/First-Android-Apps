package com.example.ujjwalsmahapatra.cloudthirdprojpushnotification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }


}
