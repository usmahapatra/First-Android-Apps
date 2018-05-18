package com.example.ujjwalsmahapatra.firstdatabasetrial;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase s=getBaseContext().openOrCreateDatabase("sqlite.db",MODE_PRIVATE,null);
        s.execSQL("insert into data values(1,"toto");");
    }
}
