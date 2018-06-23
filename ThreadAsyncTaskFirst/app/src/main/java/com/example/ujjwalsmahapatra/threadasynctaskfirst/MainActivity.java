package com.example.ujjwalsmahapatra.threadasynctaskfirst;

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
        Intent in=new Intent(MainActivity.this,MyService.class);
        startService(in);
    }
}
