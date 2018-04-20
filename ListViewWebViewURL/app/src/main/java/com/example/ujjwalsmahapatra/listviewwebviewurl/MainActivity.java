package com.example.ujjwalsmahapatra.listviewwebviewurl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String [] arr ={"Google","Facebook","Twitter","Techpalle","G Mail"};
    ListView listView;
    ArrayAdapter<String> arrayAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization:
        listView=(ListView)findViewById(R.id.listView);
        //Link between Source and Adapter
        arrayAdapter1=new ArrayAdapter<String>(this,R.layout.content_main2,arr);

        //listView with Adapter:

        listView.setAdapter(arrayAdapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int sourceArray, long destinationListView) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("selection",sourceArray);
                startActivity(intent);
            }
        });



        //

    }
}
