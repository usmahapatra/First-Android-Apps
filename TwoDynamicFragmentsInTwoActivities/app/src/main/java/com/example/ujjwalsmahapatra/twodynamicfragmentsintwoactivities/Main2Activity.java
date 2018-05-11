package com.example.ujjwalsmahapatra.twodynamicfragmentsintwoactivities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i=getIntent();
        Bundle b=i.getExtras();
        String a1=b.getString("n");
        String b1=b.getString("m");
        String c1=b.getString("e");
        String d1=b.getString("c");

        //To pass data to the fragment.. create bundle

        Bundle bundle=new Bundle();
        bundle.putString("a",a1);
        bundle.putString("b",b1);
        bundle.putString("c",c1);
        bundle.putString("d",d1);

        //creating object for FragmentTwo and sending bundle
        FragmentTwo f2=new FragmentTwo();
        f2.setArguments(bundle);

        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction trans=mgr.beginTransaction();

        trans.add(R.id.frame2,f2);
        trans.commit();
    }

}
