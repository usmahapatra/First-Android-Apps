package com.example.ujjwalsmahapatra.chattingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView t2;
    EditText e2;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Initialize
        t2=(TextView)findViewById(R.id.textView2);
        e2=(EditText)findViewById(R.id.editText2);
        b2=(Button)findViewById(R.id.button2);
        //read the data coming from the parent
        Intent in=getIntent();
        Bundle bundle=in.getExtras();
        String message=bundle.getString("parent");
        t2.setText("Parent: "+message);
        //setting OnClick listener
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send child data to parent activity
               String msg=e2.getText().toString();
               Intent i=new Intent();
               i.putExtra("child",msg);
               setResult(RESULT_OK,i);
               finish();

            }
        });

    }
}
