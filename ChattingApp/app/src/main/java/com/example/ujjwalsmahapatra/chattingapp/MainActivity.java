package com.example.ujjwalsmahapatra.chattingapp;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Request Code For Child
    public static final int REQ_CODE1=1;
EditText e1;
Button b1;
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing variables
        e1=(EditText)findViewById(R.id.editText1);
        b1=(Button)findViewById(R.id.button1);
        t1=(TextView)findViewById(R.id.textView1);
        //button onclick listener:
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,SecondActivity.class);
                String message=e1.getText().toString();
                in.putExtra("parent",message);
                startActivityForResult(in,REQ_CODE1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
            //it means child 1 has sent the message
            if(resultCode==RESULT_OK)
            {

                Bundle bnd=data.getExtras();
                String msg1=bnd.getString("child");
                t1.setText("Child:"+msg1);
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
