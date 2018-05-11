package com.example.ujjwalsmahapatra.fragmentofactivitytofragmentofanotheractivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        text=bundle.getString("editTextVal");
        //Tell the Fragment Manager
        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();
        FragmentTwo f2= (FragmentTwo) mgr.findFragmentById(R.id.fragment2);

        // send Data to the Fragment:
       if (f2!=null) {
         f2.show(text);
       }

    }

}
