package com.example.ujjwalsmahapatra.broadcastreceiver4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View view) {
        //I want to start a receiver:
       /* To send to a particular Intent
        Intent in=new Intent(MainActivity.this,MyReceiver1.class);
        sendBroadcast(in);*/
       Intent in=new Intent();
       in.setAction("MY_OWN_EVENT");
       sendOrderedBroadcast(in,null);
    }
}
