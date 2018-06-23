package com.example.ujjwalsmahapatra.cloudbasedappfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    FirebaseDatabase f;//points to our project in cloud
    DatabaseReference ref;//points to database of our project in cloud

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        //
        f=FirebaseDatabase.getInstance();//f is pointing to our project
        ref =f.getReference();//ref is pointing to the database
    }

    public void clicked(View view) {
        String name = et1.getText().toString();
        String dream=et2.getText().toString();
//now push the above two variables into the cloud database using ref variable
        //ref.child("name").setValue(name);//insert the data to the cloud
        //ref.child("dream").setValue(dream);//insert the data to the cloud
//
        HashMap<String,String> map1=new HashMap<String, String>();
        map1.put("name",name);
        map1.put("dream",dream);

        ref.push().setValue(map1);
        et1.setText("");
        et2.setText("");
        et1.requestFocus();

        Toast.makeText(this,"inserted",Toast.LENGTH_SHORT).show();

    }
}
