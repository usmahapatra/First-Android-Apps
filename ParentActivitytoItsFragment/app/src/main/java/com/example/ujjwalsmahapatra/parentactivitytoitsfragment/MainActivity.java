package com.example.ujjwalsmahapatra.parentactivitytoitsfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //3 instruction process
        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();

        FragmentOne f1= (FragmentOne) mgr.findFragmentById(R.id.fragment1);

        if(f1!=null)
        {
            f1.receiveData("Palle");
        }
    }

}
