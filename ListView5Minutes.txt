package com.example.ujjwalsmahapatra.firstprojectpalleuniv;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivitySecond extends ListActivity {
    ArrayAdapter<String> aa;
    String[] arr={"1.C# Program Compilation","2.C# Datatypes","3.C# class","4.C# class as view entity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
       aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);

       setListAdapter(aa);


    }
}
