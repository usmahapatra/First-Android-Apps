package com.example.ujjwalsmahapatra.broadcastreceiver4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Toast.makeText(context,"333333333333",Toast.LENGTH_SHORT).show();
        abortBroadcast();//break the chain
    }
}
