package com.example.ujjwalsmahapatra.dynamicfragmentfirstatpalle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button) findViewById(R.id.button1);
        et1=(EditText) findViewById(R.id.editText1);
        //getSupportFragmentManager().beginTransaction().add(R.id.container1,new FragmentOne()).commit();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et1.getText().toString();
                FragmentOne f1=new FragmentOne();
                Bundle b1=new Bundle();
                b1.putString("name",name);
                f1.setArguments(b1);
               FragmentManager mgr =getSupportFragmentManager();
               FragmentTransaction trans=mgr.beginTransaction();

               trans.replace(R.id.container1,f1);
               trans.commit();
            }
        });

        /*FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction trans=mgr.beginTransaction();

        FragmentOne f1=new FragmentOne();
        trans.add(R.id.container1,f1);
        trans.commit();*/
        //container1 is the id of the frame layout





    }
}
