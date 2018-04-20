package com.example.ujjwalsmahapatra.passingtwonumberstosecondactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText e1;
EditText e2;
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b=(Button)findViewById(R.id.button);

        //Button onclick
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1=e1.getText().toString();
                String num2=e2.getText().toString();
                Intent in=new Intent(MainActivity.this,SecondActivity.class);
                in.putExtra("num1",num1);
                in.putExtra("num2",num2);
                startActivity(in);
            }
        });
    }
}
