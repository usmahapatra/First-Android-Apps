package com.example.ujjwalsmahapatra.listviewinfragmenttoanotheractivityfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void catchData(String value){
        Intent i=new Intent(MainActivity.this,ActivitySecond.class);
        i.putExtra("value",value);
        startActivity(i);
    }
}
