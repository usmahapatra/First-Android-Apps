package com.example.ujjwalsmahapatra.getdatajsonfromhive;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public class Mytask extends AsyncTask<String,Void,StringBuilder>{

        @Override
        protected StringBuilder doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(StringBuilder stringBuilder) {
            super.onPostExecute(stringBuilder);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction trans=mgr.beginTransaction();
        FragmentOne fOne=new FragmentOne();
        trans.add(R.id.container,fOne);
        trans.commit();
    }
}
