package com.example.ujjwalsmahapatra.spinnerviewfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrayList1;
    ArrayAdapter<String> arrayAdapter;
    Spinner spinner1;

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the three objects:
        arrayList1=new ArrayList<String>();
        editText=(EditText) findViewById(R.id.editText1);
        button=(Button)findViewById(R.id.button1);

        spinner1=(Spinner) findViewById(R.id.spinner1);

        //adapter to arrayList:

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayList1);

        //spinner to adapter link:

        spinner1.setAdapter(arrayAdapter);

        //button set on click listen:

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                //add element to the arrayList(i.e. the source)(:
                arrayList1.add(name);

                //alert the adapter:

                arrayAdapter.notifyDataSetChanged();
            }
        });

    }
}
