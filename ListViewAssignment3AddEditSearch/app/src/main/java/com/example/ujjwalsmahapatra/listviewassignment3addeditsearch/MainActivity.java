package com.example.ujjwalsmahapatra.listviewassignment3addeditsearch;

import android.app.AlertDialog;
import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Decraration
    static int position;
    Button button1;
    Button button2;
    Button button3;
    EditText editText1;
    ListView listView1;
    ArrayList<String> arrayList1;
    ArrayAdapter<String> arrayAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        editText1=(EditText)findViewById(R.id.editText1);
        listView1=(ListView)findViewById(R.id.listView1);
        arrayList1=new ArrayList<>();

        //Adapter to ArrayList

        arrayAdapter1=new ArrayAdapter<String>(this,R.layout.content_city,arrayList1);

        //ListView to Adapter

        listView1.setAdapter(arrayAdapter1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city_name=editText1.getText().toString();
                arrayList1.add(city_name);
                arrayAdapter1.notifyDataSetChanged();
                editText1.setText("");
                editText1.requestFocus();
            }
        });


        //Search:

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city_name=editText1.getText().toString();
                int ind=arrayList1.indexOf(city_name);
                Toast.makeText(MainActivity.this,  ind+"", Toast.LENGTH_LONG).show();
                editText1.setText("");
                editText1.requestFocus();

            }
        });

        //Edit

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city=arrayList1.get(i);
                editText1.setText(city);
                position=i;

            }
        });


         button2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String edited=editText1.getText().toString();
                 arrayList1.set(position,edited);
                 arrayAdapter1.notifyDataSetChanged();
                 editText1.setText("");
             }
         });


         //Delete Item:

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("Are you Sure to Delete the Item: ");

                arrayList1.remove(i);
                arrayAdapter1.notifyDataSetChanged();
                //following must be true for proper functioning of Long Click
                return true;
            }
        });
    }
}
