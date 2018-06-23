package com.example.ujjwalsmahapatra.viewpagerpractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTime extends Fragment {
    ImageView imageView;
    int [] images={R.drawable.androiddev,R.drawable.apply,R.drawable.java};

    public FragmentTime() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_fragment_time, container, false);
        imageView=v.findViewById(R.id.imageView);
        Bundle b=getArguments();
        int select=b.getInt("position");
        imageView.setImageResource(images[select]);
        return v;
    }

}
