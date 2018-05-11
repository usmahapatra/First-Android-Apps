package com.example.ujjwalsmahapatra.parentactivitytoitsfragment;


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


    String val;
    TextView textView;
    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        textView=(TextView)v.findViewById(R.id.textView);

         return v;
    }

    public void receiveData(String value)
    {
        textView.setText(value);
    }
}
