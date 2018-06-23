package com.example.ujjwalsmahapatra.broadcastreceiverfirst;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.startActivity;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String event =intent.getAction();//gives us the event name
        if(event==Intent.ACTION_POWER_CONNECTED)
        {
        Toast.makeText(context,"power cable attached",Toast.LENGTH_SHORT).show();
        }
        else if (event==Intent.ACTION_POWER_DISCONNECTED)
            Toast.makeText(context,"power cable removed",Toast.LENGTH_SHORT).show();
        else if (event==Intent.ACTION_BOOT_COMPLETED) {
            Intent in = new Intent(context, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//Mandatory
            context.startActivity(in);
        }
    }
}
