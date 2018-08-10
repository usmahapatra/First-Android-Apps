package com.example.ujjwalsmahapatra.zomatoappprojectmapactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction t=mgr.beginTransaction();
        FragmentOne m=new FragmentOne();
        t.add(R.id.container1,m);
        t.commit();


    }

}
