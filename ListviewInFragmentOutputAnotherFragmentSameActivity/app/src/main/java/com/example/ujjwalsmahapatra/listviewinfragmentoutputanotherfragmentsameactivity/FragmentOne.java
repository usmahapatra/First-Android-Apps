package com.example.ujjwalsmahapatra.listviewinfragmentoutputanotherfragmentsameactivity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
     EditText editText1,editText2,editText3;
     Button button;
    ListView listView1;
    ArrayList<Student> arrayList;
    MyAdapter m;
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // student object is saved
            Student s=arrayList.get(i);

            //View reference is taken

            View v=getLayoutInflater().inflate(R.layout.content_row,null);

            TextView tv1=(TextView)v.findViewById(R.id.text1);
            TextView tv2=(TextView)v.findViewById(R.id.text2);
            TextView tv3=(TextView) v.findViewById(R.id.text3);

            tv1.setText(s.getName());
            tv2.setText(s.getRoll_no());
            tv3.setText(s.getSubject());
            return v;
        }
    }

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        editText1=(EditText)v.findViewById(R.id.editText1);
        editText2=(EditText) v.findViewById(R.id.editText2);
        editText3=(EditText) v.findViewById(R.id.editText3);
        button=(Button) v.findViewById(R.id.button);
        listView1=(ListView)v.findViewById(R.id.list1);
        //initialize the arrayList:
        arrayList=new ArrayList<Student>();
        //adapter to the listView is not necessary because MyAdapter is an inner class

        m=new MyAdapter();

        //link between the Adapter and the listView:

        listView1.setAdapter(m);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student=new Student();
                student.setName(editText1.getText().toString());
                student.setRoll_no(editText2.getText().toString());
                student.setSubject(editText3.getText().toString());
                arrayList.add(student);
                m.notifyDataSetChanged();
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student temp =arrayList.get(i);
                //
                String name=temp.getName();
                String subject=temp.getSubject();
                String roll=temp.getRoll_no();


                //I have commited mistake while writing the below line:

                MainActivity m=(MainActivity) getActivity();
                m.listClickData(name,subject,roll);

            }
        });
        return v;
    }

}
