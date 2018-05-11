package com.example.ujjwalsmahapatra.twodynamicfragmentsintwoactivities;


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

    TextView textView1,textView2,textView3,textView4;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_two, container, false);
        textView1=(TextView)v.findViewById(R.id.text1);
        textView2=(TextView)v.findViewById(R.id.text2);
        textView3=(TextView)v.findViewById(R.id.text3);
        textView4=(TextView)v.findViewById(R.id.text4);

        //get the values from bundle:

        Bundle bundle=getArguments();
        if(bundle!=null)
        {

            String name= bundle.getString("a");
            String mob= bundle.getString("b");
            String email=bundle.getString("c");
            String country=bundle.getString("d");
            textView1.setText(name);
            textView2.setText(mob);
            textView3.setText(email);
            textView4.setText(country);
        }

        return v;
    }

}
