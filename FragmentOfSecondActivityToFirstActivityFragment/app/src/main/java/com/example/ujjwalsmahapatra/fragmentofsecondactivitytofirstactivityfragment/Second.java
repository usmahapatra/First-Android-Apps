package com.example.ujjwalsmahapatra.fragmentofsecondactivitytofirstactivityfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



    }

    public void catchData(String value)
    {
     Intent i= new Intent();
     i.putExtra("value",value);
     setResult(RESULT_OK,i);
     finish();
    }

}
