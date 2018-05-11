package com.example.ujjwalsmahapatra.fragmentdatapassparent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void catchData(String data)
    {
        Toast.makeText(this,"Entered Name: "+data,Toast.LENGTH_LONG).show();
    }
}
