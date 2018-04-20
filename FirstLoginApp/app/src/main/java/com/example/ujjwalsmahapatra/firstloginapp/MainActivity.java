package com.example.ujjwalsmahapatra.firstloginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText e1;
    EditText e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization
        b=(Button)findViewById(R.id.button);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        //click Listening
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= e1.getText().toString();
                String password= e2.getText().toString();
                //Validation of user name and password
                if(name.equals("palle") && password.equals("university"))
                {
                    Intent in=new Intent(MainActivity.this,SecondScreen.class);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid user name and password",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
