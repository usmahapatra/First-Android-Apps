package com.example.ujjwalsmahapatra.preferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView1,textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);

        SharedPreferences sp= getSharedPreferences("Credentials",0);

        String uname=sp.getString("uname",null);
        String pw=sp.getString("pw",null);

        textView1.setText(uname);
        textView2.setText(pw);
    }
}
