package com.example.ujjwalsmahapatra.passingdatatoanotheractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecievingActivity extends AppCompatActivity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieving);
        t=(TextView)findViewById(R.id.textView);
        Intent in=getIntent();
        Bundle b=in.getExtras();
        String city=b.getString("city");
        t.setText(city);
    }
}
