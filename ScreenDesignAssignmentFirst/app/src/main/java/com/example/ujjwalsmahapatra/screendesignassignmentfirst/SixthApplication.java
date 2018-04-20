package com.example.ujjwalsmahapatra.screendesignassignmentfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SixthApplication extends AppCompatActivity {
 TextView tv3,tv4,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_application);
        tv3=(TextView)findViewById(R.id.textView3);
        tv4=(TextView)findViewById(R.id.textView4);
        tv5=(TextView)findViewById(R.id.textView5);
        String text3=tv3.getText().toString();
        String text4=tv4.getText().toString();
        tv5.setText(text3+" "+text4);
        Toast.makeText(this,text3+" "+text4,Toast.LENGTH_LONG).show();
    }
}
