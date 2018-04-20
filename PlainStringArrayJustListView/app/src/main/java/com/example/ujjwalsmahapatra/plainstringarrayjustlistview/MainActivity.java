package com.example.ujjwalsmahapatra.plainstringarrayjustlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String [] arr={"SETTINGS","RINGTONE","VIBRATION MODE","DISPLAY","SCREEN SAVER","ABOUT PHONE","IMEI NUMBER","LOCATION","WIRELESS SETTINGS"};
    ListView listView1;
    ArrayAdapter<String> arrayAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization:
        listView1=(ListView) findViewById(R.id.listview1);//I forgot to mention this line

        //Link between Array to Adapter

        arrayAdapter1=new ArrayAdapter(this,R.layout.content_plain_array,arr);

        //Link between ListView to Adapter

        listView1.setAdapter(arrayAdapter1);

        //arrayAdapter1.notifyDataSetChanged(); function is performed by the above instruction

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arr[i], Toast.LENGTH_LONG).show();
            }
        });
    }



}
