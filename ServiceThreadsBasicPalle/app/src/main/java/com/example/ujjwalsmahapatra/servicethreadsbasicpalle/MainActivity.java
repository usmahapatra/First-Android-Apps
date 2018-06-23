package com.example.ujjwalsmahapatra.servicethreadsbasicpalle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter inf=new IntentFilter();
        inf.addAction("ShowToast");
        registerReceiver(new MyReceiver(),inf);
    }
//dynamic broadcast: 
    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"hiii",Toast.LENGTH_SHORT).show();
        }
    }

    public void clicked(View view) {
    Intent in=new Intent(MainActivity.this,MyService.class);
    startService(in);
    }
}
