package com.example.anrinyanath.zomatoexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new RestunantFrag()).commit();
    }

    public void startMap() {
        Intent i=new Intent(HomeActivity.this,MapsActivity.class);
        startActivity(i);
    }
}
