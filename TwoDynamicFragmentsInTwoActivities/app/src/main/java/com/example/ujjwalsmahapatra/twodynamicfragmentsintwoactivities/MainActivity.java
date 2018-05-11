package com.example.ujjwalsmahapatra.twodynamicfragmentsintwoactivities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentOne f1=new FragmentOne();
        android.support.v4.app.FragmentManager mgr= getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction trans= mgr.beginTransaction();

        trans.add(R.id.frame1,f1);
        trans.commit();

    }
    public void catchData(String name,String mob,String email,String country)
    {
       Intent b = new Intent(MainActivity.this,Main2Activity.class);
       b.putExtra("n",name);
       b.putExtra("m",mob);
       b.putExtra("e",email);
       b.putExtra("c",country);

       startActivity(b);

    }
}
