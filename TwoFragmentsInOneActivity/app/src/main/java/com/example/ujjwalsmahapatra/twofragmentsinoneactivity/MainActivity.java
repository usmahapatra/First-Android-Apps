package com.example.ujjwalsmahapatra.twofragmentsinoneactivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loading Fragment:
        FragmentOne loginFragment=new FragmentOne();
        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction trans =mgr.beginTransaction();
        trans.add(R.id.frame1,loginFragment);
        trans.commit();
    }
    public void catchData(String name)
    {
        FragmentTwoWelcome w=new FragmentTwoWelcome();
        Bundle b=new Bundle();
        b.putString("name",name);
        w.setArguments(b);
        //Replacing the older fragment with the new one:

        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction trans=mgr.beginTransaction();

        //to replace the older fragment:

        trans.replace(R.id.frame1,w);
        trans.commit();

    }
}
