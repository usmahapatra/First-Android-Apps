package com.example.ujjwalsmahapatra.passingdatatoanotheractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization
        b=(Button)findViewById(R.id.button1);
        t=(TextView)findViewById(R.id.editText);
        //
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,RecievingActivity.class);
                String city=t.getText().toString();
                in.putExtra("city",city);
                startActivity(in);
            }
        });
    }
}
