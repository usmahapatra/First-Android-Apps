package com.example.ujjwalsmahapatra.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize:

        editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        button=(Button) findViewById(R.id.button);

    }

    public void clicked(View view) {


        SharedPreferences sp=getSharedPreferences("Credentials",0);

        SharedPreferences.Editor ed=sp.edit();

        ed.putString("uname",editText1.getText().toString());
        ed.putString("pw",editText2.getText().toString());

        ed.apply();
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);

        startActivity(intent);
    }
}
