package com.example.ujjwalsmahapatra.firsttimefirstactivitysecondtimeanother;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//I have added the code
import android.os.Handler;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=getSharedPreferences("Settings",0);

        boolean firstTime= sharedPreferences.getBoolean("firsttime",true);

        if (firstTime)
        {
            SharedPreferences.Editor ed= sharedPreferences.edit();
            ed.putBoolean("firsttime",false);
            ed.apply();


        }
        else
        {
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run(){
                            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    ,5000);
        }
    }
}
