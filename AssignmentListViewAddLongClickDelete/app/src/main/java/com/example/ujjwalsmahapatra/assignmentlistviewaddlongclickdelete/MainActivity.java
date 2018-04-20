package com.example.ujjwalsmahapatra.assignmentlistviewaddlongclickdelete;

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
    Button button1;
    EditText editText1;
    ListView listView1;
    ArrayList<String> arrayList1;
    ArrayAdapter<String> arrayAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialisation
        button1=(Button)findViewById(R.id.button1);
        editText1=(EditText)findViewById(R.id.editText1);
        listView1=(ListView)findViewById(R.id.listView1);
        arrayList1=new ArrayList<>();
        //.....Link.....Adapter to ArrayList:
        arrayAdapter1=new ArrayAdapter<String>(this,R.layout.for_adapter,arrayList1);
        //.....Link.....ListView to Adapter:
        listView1.setAdapter(arrayAdapter1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heroName=editText1.getText().toString();
                int count=1+arrayList1.size()/2;
                arrayList1.add(count+". "+heroName);
                arrayAdapter1.notifyDataSetChanged();
                arrayList1.add("");
                arrayAdapter1.notifyDataSetChanged();
                editText1.setText("");
                editText1.requestFocus();

            }
        });

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=arrayList1.get(i);
                //if(i%2==0) logic on position
                if(name.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Invalid Cell",Toast.LENGTH_LONG).show();
                return true;
                }

                arrayList1.remove(i);
                arrayList1.remove(i);
                arrayAdapter1.notifyDataSetChanged();
                return true;
            }
        });

    }
}
