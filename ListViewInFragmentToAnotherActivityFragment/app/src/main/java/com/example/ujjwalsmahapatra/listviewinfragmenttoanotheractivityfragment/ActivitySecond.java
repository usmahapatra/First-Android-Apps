package com.example.ujjwalsmahapatra.listviewinfragmenttoanotheractivityfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        String value=bundle.getString("value");

        FragmentManager mgr=getSupportFragmentManager();
        FragmentTwo f2=(FragmentTwo)mgr.findFragmentById(R.id.frag2);
        f2.catchData(value);
    }

}
