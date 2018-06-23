package com.example.ujjwalsmahapatra.pallefirstprojectusingdynamicfragments;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListView listView;
    MyAdapter myAdapter;
    ArrayList<Video> arr;

    public class MyAdapter extends BaseAdapter
    {

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
            //LayoutInflater inflater = getActivity().getLayoutInflater();
            final Video vid=arr.get(i);
            View v= getActivity().getLayoutInflater().inflate(R.layout.row,null);
            TextView t=(TextView) v.findViewById(R.id.text1);

            TextView t2=(TextView)v.findViewById(R.id.textView_vidName);
            TextView t3=(TextView)v.findViewById(R.id.textView_vidDuration);
            Button b1=(Button) v.findViewById(R.id.button1);


            t.setText(vid.getSerial_no());
            t2.setText(vid.getVid_name());
            t3.setText(vid.getVid_duration());
            // Button Set On Click
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity m=(MainActivity) getActivity();
                    m.receive2(vid.getVideo_id());
                    //if(getInternetStatus()==true) {

                    // }
                    // else{
                    //Toast.makeText(ActivitySecond.this,"Check Internet Availability",Toast.LENGTH_LONG).show();
                    // }
                }
            });


            return v;
        }
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTwo newInstance(String param1, String param2) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_two, container, false);
        listView=(ListView) v.findViewById(R.id.listView);
        arr=new  <Video>ArrayList();
        myAdapter=new MyAdapter();
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
                "Indexes/hrVpqW_Bh2o/21:09"};
        listView=(ListView)v.findViewById(R.id.listView);
        listView.setAdapter(myAdapter);


        switch (mParam1) {
            case "1": {
                for (int i = 0; i < src1.length; i++) {
                    String sub = src1[i];
                    int fpos = sub.indexOf("/");
                    int spos = sub.indexOf("/", fpos + 1);
                    String vName = sub.substring(0, fpos);
                    String vId = sub.substring(fpos + 1, spos);
                    String vDuration = sub.substring(spos + 1);
                    Video video = new Video(vId, vDuration, vName, (i + 1) + "");

                    arr.add(video);


                }
                myAdapter.notifyDataSetChanged();
                break;
            }
            case "2":
                for (int i = 0; i < src2.length; i++) {
                    String sub = src2[i];
                    int fpos = sub.indexOf("/");
                    int spos = sub.indexOf("/", fpos + 1);
                    String vName = sub.substring(0, fpos);
                    String vId = sub.substring(fpos + 1, spos);
                    String vDuration = sub.substring(spos + 1);
                    Video video = new Video(vId, vDuration, vName, (i + 1) + "");

                    arr.add(video);


                }
                myAdapter.notifyDataSetChanged();
                break;
            case "3":
                for (int i = 0; i < src3.length; i++) {
                    String sub = src3[i];
                    int fpos = sub.indexOf("/");
                    int spos = sub.indexOf("/", fpos + 1);
                    String vName = sub.substring(0, fpos);
                    String vId = sub.substring(fpos + 1, spos);
                    String vDuration = sub.substring(spos + 1);
                    Video video = new Video(vId, vDuration, vName, (i + 1) + "");

                    arr.add(video);


                }
                myAdapter.notifyDataSetChanged();
                break;
            default:
                Toast.makeText(getActivity(), "nonononono", Toast.LENGTH_LONG).show();

        }

                return  v;
    }

}
