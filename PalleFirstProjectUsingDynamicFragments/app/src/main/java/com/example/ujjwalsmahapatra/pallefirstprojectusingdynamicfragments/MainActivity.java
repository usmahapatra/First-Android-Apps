package com.example.ujjwalsmahapatra.pallefirstprojectusingdynamicfragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentOne f1=new FragmentOne();
        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction transaction=mgr.beginTransaction();

        transaction.add(R.id.container,f1);
        transaction.commit();

    }
    public void receive(int selection)
    {
        FragmentTwo fragmentTwo=FragmentTwo.newInstance(""+selection,null);
        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction=mgr.beginTransaction();
        transaction.replace(R.id.container,fragmentTwo);
        transaction.commit();
    }

    public void receive2(String vidId)
    {
        FragmentThree fragmentThree=FragmentThree.newInstance(vidId,null);
        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction trans=mgr.beginTransaction();
        trans.replace(R.id.container,fragmentThree);
        trans.commit();
    }

}
