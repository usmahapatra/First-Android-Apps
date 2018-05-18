package com.example.ujjwalsmahapatra.listviewinfragmentoutputanotherfragmentsameactivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {
    TextView textView1,textView2,textView3;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_two, container, false);
        Bundle bundle=getArguments();
        textView1=(TextView)v.findViewById(R.id.tv1);
        textView2=(TextView)v.findViewById(R.id.tv2);
        textView3=(TextView)v.findViewById(R.id.tv3);
        textView1.setText(bundle.getString("a"));
        textView2.setText(bundle.getString("b"));
        textView3.setText(bundle.getString("c"));
        return v;
    }

}
