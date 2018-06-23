package com.example.ujjwalsmahapatra.servicefirstpalle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start Service:




        //Stop Service:

       Intent i=new Intent(MainActivity.this,MyService.class);
       stopService(i);

    }

    public void clicked(View view) {
        Intent in=new Intent(MainActivity.this,MyService.class);
        switch(view.getId()){
            case R.id.button1:
                startService(in);
                break;
            case R.id.button2:
                stopService(in);
                break;
        }
    }
}
