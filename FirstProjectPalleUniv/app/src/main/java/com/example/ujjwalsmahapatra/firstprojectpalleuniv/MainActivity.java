package com.example.ujjwalsmahapatra.firstprojectpalleuniv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Palle Technologies");

        //Initialize all the references:

        linearLayout1 =(LinearLayout) findViewById(R.id.linear1);
        linearLayout2 =(LinearLayout) findViewById(R.id.linear2);
        linearLayout3 =(LinearLayout) findViewById(R.id.linear3);

        //onclick events for the three linear layouts:

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,ActivitySecond.class);
                intent1.putExtra("linear1",1);
                startActivity(intent1);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,ActivitySecond.class);
                intent1.putExtra("linear2",2);
                startActivity(intent1);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this, ActivitySecond.class);
                intent1.putExtra("linear3",3);
                startActivity(intent1);
            }
        });
    }
}
