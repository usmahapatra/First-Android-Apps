package com.example.ujjwalsmahapatra.spinnersecondselecteddisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String [] course={"Select Course","B.Tech","B.E.","M.C.A","M.Tech","M.B.A."};
    EditText editText;
    Spinner spinner;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> arrayAdapter;

        //Initialization:

        editText=(EditText)findViewById(R.id.editText);
        spinner=(Spinner)findViewById(R.id.spinner);
        textView=(TextView)findViewById(R.id.textView);

        //Array Adapter to the Array:

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,course);

        //spinner to the array adapter:

        spinner.setAdapter(arrayAdapter);
        //spinner click Item:

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText("Entered details:\n"+"Name: "+editText.getText().toString()+"\nQualification: "+course[i]);
                editText.setText("");
                editText.requestFocus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("Entered details:\n"+"Name: "+"\nQualification: ");
            }
        });
    }
}
