package com.example.ujjwalsmahapatra.pallefirstprojectusingdynamicfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        linearLayout1 =(LinearLayout) v.findViewById(R.id.linear1);
        linearLayout2 =(LinearLayout) v.findViewById(R.id.linear2);
        linearLayout3 =(LinearLayout) v.findViewById(R.id.linear3);

        //onclick events for the three linear layouts:

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity=(MainActivity) getActivity();
                mainActivity.receive(1);
               // intent1.putExtra("course",1);
                //startActivity(intent1);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity=(MainActivity) getActivity();
                mainActivity.receive(2);
                //intent1.putExtra("course",2);
                //startActivity(intent1);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity=(MainActivity) getActivity();
                mainActivity.receive(3);
                //intent1.putExtra("course",3);
                //startActivity(intent1);
            }
        });
        return v;
    }

}
