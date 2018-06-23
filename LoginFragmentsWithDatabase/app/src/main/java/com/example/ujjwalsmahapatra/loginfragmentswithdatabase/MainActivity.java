package com.example.ujjwalsmahapatra.loginfragmentswithdatabase;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database objects initialization:
        mdb=new MyDatabase(this);
        mdb.open();

        LoginFragment lf=new LoginFragment();
        android.support.v4.app.FragmentManager fmgr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction trans=fmgr.beginTransaction();
        trans.add(R.id.container1,lf);
        trans.commit();

    }

    public void receiveStudentDetails(String name,String sub)
    {
        mdb.insertStudent(name, sub);
        Toast.makeText(MainActivity.this,"Student Details are Inserted",Toast.LENGTH_SHORT).show();
    }

    public void show()
    {
        DisplayFragment df=new DisplayFragment();
        android.support.v4.app.FragmentManager mgr=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction trans=mgr.beginTransaction();
        trans.replace(R.id.container1,df);
        trans.addToBackStack(null);
        trans.commit();

    }
}
