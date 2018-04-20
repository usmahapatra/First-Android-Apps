package com.example.ujjwalsmahapatra.listviewarrayadapterassign2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declaration of View Variables
    Button button1;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    ListView listView1;
    Employee emp;
    ArrayList<String> employee ;
    ArrayAdapter<String> arrayAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization
        button1=(Button)findViewById(R.id.button1);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        listView1=(ListView)findViewById(R.id.listView1);
        emp=new Employee();
        employee=new ArrayList<String>();


        //Link the arrayAdapter with ArrayList

        arrayAdapter1=new ArrayAdapter<String>(this,R.layout.content_employee,employee);


        //Link the ListView to the Adapter

        listView1.setAdapter(arrayAdapter1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee ep1=new Employee();
                ep1.name=editText1.getText().toString();
                ep1.salary=Integer.parseInt(editText2.getText().toString());
                ep1.designation=editText3.getText().toString();
                employee.add(ep1.name+"\n"+ep1.salary+"\n"+ep1.designation);
                arrayAdapter1.notifyDataSetChanged();
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });


    }
}
