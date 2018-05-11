package com.example.ujjwalsmahapatra.singleactivitytwofragmentsdatapass;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void receive(String value)
    {
        android.support.v4.app.FragmentManager mgr= getSupportFragmentManager();
        FragmentTwo f2=(FragmentTwo)mgr.findFragmentById(R.id.frag2);
        if(f2!=null) {
            f2.receive(value);
        }

    }
}
