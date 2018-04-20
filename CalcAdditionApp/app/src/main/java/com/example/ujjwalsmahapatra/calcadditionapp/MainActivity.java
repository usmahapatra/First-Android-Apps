package com.example.ujjwalsmahapatra.calcadditionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    TextView etResult;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText) findViewById(R.id.EditText1);
        et2=(EditText) findViewById(R.id.EditText2);
        etResult=(TextView) findViewById(R.id.TextView3);
        bt=(Button)findViewById(R.id.Button1);

        bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String e1= et1.getText().toString();
                String e2= et2.getText().toString();
                int num1=Integer.parseInt(e1);
                int num2=Integer.parseInt(e2);

                etResult.setText(""+(num1+num2));
            }
        });

    }
}
