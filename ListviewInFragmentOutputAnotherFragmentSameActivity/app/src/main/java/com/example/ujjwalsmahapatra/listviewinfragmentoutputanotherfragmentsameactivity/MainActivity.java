package com.example.ujjwalsmahapatra.listviewinfragmentoutputanotherfragmentsameactivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction transaction=mgr.beginTransaction();
        FragmentOne f1=new FragmentOne();
        transaction.add(R.id.frame1,f1);
        transaction.commit();
    }
    public void listClickData(String name,String subject,String roll)
    {
        FragmentTwo f2=new FragmentTwo();

        Bundle bundle=new Bundle();
        bundle.putString("a",name);
        bundle.putString("b",subject);
        bundle.putString("c",roll);
        f2.setArguments(bundle);
        FragmentManager mgr=getSupportFragmentManager();

        FragmentTransaction transaction=mgr.beginTransaction();

        transaction.replace(R.id.frame1,f2);
        //to come back to the first fragment by clicking the back button
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
