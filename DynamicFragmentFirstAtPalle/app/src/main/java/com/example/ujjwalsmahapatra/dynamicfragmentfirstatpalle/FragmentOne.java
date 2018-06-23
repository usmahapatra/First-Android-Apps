package com.example.ujjwalsmahapatra.dynamicfragmentfirstatpalle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fragment_one, container, false);
        TextView textView=(TextView)v.findViewById(R.id.text1);
        Bundle bundle=getArguments();
        if(bundle!=null){
        String n=bundle.getString("name");
        textView.setText(n);}



        return v;
    }

}
