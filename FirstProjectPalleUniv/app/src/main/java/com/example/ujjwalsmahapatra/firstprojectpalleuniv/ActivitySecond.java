package com.example.ujjwalsmahapatra.firstprojectpalleuniv;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ActivitySecond extends ListActivity {
    MyAdapter myAdapter;
    ArrayList<Video> arr;

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arr.size();
        }

        @Override
        public Object getItem(int i) {
            return arr.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Video vid=arr.get(i);
            View v= getLayoutInflater().inflate(R.layout.activity_row,null);
            TextView t1=(TextView) v.findViewById(R.id.textView_serialNo);
            TextView t2=(TextView)v.findViewById(R.id.textView_vidName);
            TextView t3=(TextView)v.findViewById(R.id.textView_vidDuration);

            t1.setText(vid.getSerial_no());
            t2.setText(vid.getVid_name());
            t3.setText(vid.getVid_duration());




            return v;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
       myAdapter=new MyAdapter();
       arr=new ArrayList<Video>();

       setListAdapter(myAdapter);

      //Intent->bundle->course ...get the course

        Intent in=getIntent();
        Bundle bundle=in.getExtras();
        Integer course=bundle.getInt("course");
        switch (course)
        {
            case 1: {
                Video first = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "1");
                Video second = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "2");
                Video third = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "3");
                Video fourth = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "4");
                Video fifth = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "5");
                Video sixth = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "6");
                Video seventh = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "7");
                Video eighth = new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "8");
                Video ninth= new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "9");
                Video tenth= new Video("ltgDdukzQ7I", "18:47", "C#Program Compilation", "10");
                arr.add(first);

                arr.add(second);

                arr.add(third);

                arr.add(fourth);

                arr.add(fifth);

                arr.add(sixth);

                arr.add(seventh);

                arr.add(eighth);

                arr.add(ninth);

                arr.add(tenth);
                myAdapter.notifyDataSetChanged();
                break;
            }
            case 2:
                Toast.makeText(this,"for professionals",Toast.LENGTH_LONG).show();//function2();
                break;
            case 3:
                Toast.makeText(this,"SQL",Toast.LENGTH_LONG).show();//function3();
                break;
                default:
                    Toast.makeText(this,"nonononono",Toast.LENGTH_LONG).show();;
        }


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
       // Toast.makeText(ActivitySecond.this,arr[position],Toast.LENGTH_LONG).show();
        super.onListItemClick(l, v, position, id);
    }
}
