package com.example.ujjwalsmahapatra.oneactivitymultiplefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    TextView textView;


    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_welcome, container, false);
        textView=(TextView) v.findViewById(R.id.textView1);
        Bundle b=getArguments();
        String uid="Welcome\n"+b.getString("uid",null);
        textView.setText(uid);

        return v;
    }

}
