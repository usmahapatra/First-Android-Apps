package com.example.ujjwalsmahapatra.twofragmentsinoneactivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwoWelcome extends Fragment {

    TextView textView;

    public FragmentTwoWelcome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_two_welcome, container, false);
        textView=(TextView)v.findViewById(R.id.text1);
        Bundle bundle=getArguments();
        String name=bundle.getString("name");
        textView.setText(name);
        return v;
    }

}
