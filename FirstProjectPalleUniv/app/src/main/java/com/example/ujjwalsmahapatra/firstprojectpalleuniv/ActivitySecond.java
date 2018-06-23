package com.example.ujjwalsmahapatra.firstprojectpalleuniv;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
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
            final Video vid=arr.get(i);
            View v= getLayoutInflater().inflate(R.layout.activity_row,null);
            TextView t1=(TextView) v.findViewById(R.id.textView_serialNo);
            TextView t2=(TextView)v.findViewById(R.id.textView_vidName);
            TextView t3=(TextView)v.findViewById(R.id.textView_vidDuration);
            Button b1=(Button) v.findViewById(R.id.button1);


            t1.setText(vid.getSerial_no());
            t2.setText(vid.getVid_name());
            t3.setText(vid.getVid_duration());
            // Button Set On Click
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getInternetStatus()==true) {
                        Intent i = new Intent(ActivitySecond.this, Thr3dActivity.class);
                        String vid_id = vid.getVideo_id();
                        i.putExtra("vid_id", vid_id);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(ActivitySecond.this,"Check Internet Availability",Toast.LENGTH_LONG).show();
                   }
                }
            });


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
        String[] src1={
                "C# program compilation/ltgDdukzQ7I/18:47",
        "C# data types/L_gFuuSp4V0/17:53",
                "C# class/l1C4FZGCab0/10:48",
                "C# class as virtual entity/HSdIq3k51bg/9:15",
                "Objects in c#/SM_QqUdMXY0/22:14",
                "Debugging in visual studio/8hXH5HxQfFU/10:41",
                "C# Arrays/CLnA6OAlNPk/24:50",
                "Declaring and Modifying data in c# arrays/O2QI3YFupxM/9:06",
                "Arrays Assignment/Zt85ireWQWw/7:49",
                "Loops/u_k75WcEpHM/5:51"};
        String[] src2={
                "C# program compilation/ltgDdukzQ7I/18:47",
                "Debugging in visual studio/8hXH5HxQfFU/10:41",
                "Inheritence/TOBLe0qoA_o/9:24",
                "Inheritence Part 2/T7G8NFXDXFE/24:07",
                "base keyword/WaAbIMz2dqg/22:08",
                "Overriding Intro/w6ldKhR4YUs/23:31",
                "overriding an override method/fdPslUmRqm0/12:52",
                "Static Variables/zvk_hS4vEOw/9:27",
                "what is the use of properties in c#/UdiU6sp68Tc/16:16",
                "c# properties/5nHmt5Zi7l8/15:03"};
        String[] src3={
                "SQLServer Overview/Kdc84lpF4GM/16:25",
                "Normalization/7Dc7_I48ZTg/14:30",
                "Orderby clause/quuwLXzZl3g/6:59",
                "Delete drop and truncate statements/yZNyUzSMsT8/6:58",
                "Aggregate Functions/2IYykxAXaB8/13:54",
                "Group by clause/qx0j5iWajqg/20:22",
                "Joins and Inner Join with simple explanation/i0vwTFFHTU8/33:02",
                "stored procedure/jmZsXlAYe7Y/17:11",
                "User defined functions in sql/8cJFtDESxiQ/9:01",
                "Indexes/hrVpqW_Bh2o/21:09"
        };
        Integer course=bundle.getInt("course");
        switch (course)
        {
            case 1: {
                for(int i=0;i<src1.length;i++) {
                    String sub=src1[i];
                    int fpos=sub.indexOf("/");
                    int spos=sub.indexOf("/",fpos+1);
                    String vName=sub.substring(0,fpos);
                    String vId=sub.substring(fpos+1,spos);
                    String vDuration=sub.substring(spos+1);
                    Video video=new Video(vId,vDuration,vName,(i+1)+"");

                    arr.add(video);


                }
                myAdapter.notifyDataSetChanged();
                break;
            }
            case 2:
                for(int i=0;i<src2.length;i++) {
                    String sub=src2[i];
                    int fpos=sub.indexOf("/");
                    int spos=sub.indexOf("/",fpos+1);
                    String vName=sub.substring(0,fpos);
                    String vId=sub.substring(fpos+1,spos);
                    String vDuration=sub.substring(spos+1);
                    Video video=new Video(vId,vDuration,vName,(i+1)+"");

                    arr.add(video);


                }
                myAdapter.notifyDataSetChanged();
                break;
            case 3:
                for(int i=0;i<src3.length;i++) {
                    String sub=src3[i];
                    int fpos=sub.indexOf("/");
                    int spos=sub.indexOf("/",fpos+1);
                    String vName=sub.substring(0,fpos);
                    String vId=sub.substring(fpos+1,spos);
                    String vDuration=sub.substring(spos+1);
                    Video video=new Video(vId,vDuration,vName,(i+1)+"");

                    arr.add(video);


                }
                myAdapter.notifyDataSetChanged();
                break;
                default:
                    Toast.makeText(this,"nonononono",Toast.LENGTH_LONG).show();
        }


    }

    public boolean getInternetStatus(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

            if (networkInfo!=null&&networkInfo.isConnected())
            {
                return true;
            }else return false;

        }else return false;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
       // Toast.makeText(ActivitySecond.this,arr[position],Toast.LENGTH_LONG).show();
        super.onListItemClick(l, v, position, id);
    }
}
