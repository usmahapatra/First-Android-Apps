package com.example.ujjwalsmahapatra.dynamicfragrentsassignment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editText1);
        textView=(TextView)findViewById(R.id.textView1);
        button=(Button)findViewById(R.id.button1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager mgr=getSupportFragmentManager();
                final FragmentTransaction trans=mgr.beginTransaction();
                final FragmentOne f1=new FragmentOne();
                trans.add(R.id.container1,f1);

                String name=editText.getText().toString();
                Bundle bn= new Bundle();
                bn.putString("name","Name: "+name);
                f1.setArguments(bn);
                trans.commit();

            }
        });
    }
    public void receive(String qualification)
    {
        textView.setText(qualification);
    }
}
