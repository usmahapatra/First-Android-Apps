package com.example.ujjwalsmahapatra.passingtwonumberstosecondactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Initialize
        t=(TextView)findViewById(R.id.textView);
        Intent in=getIntent();
        Bundle b=in.getExtras();
        String num1=b.getString("num1");
        String num2=b.getString("num2");
        int n1=Integer.parseInt(num1);
        int n2= Integer.parseInt(num2);
        t.setText(""+(n1+n2));
    }
}
