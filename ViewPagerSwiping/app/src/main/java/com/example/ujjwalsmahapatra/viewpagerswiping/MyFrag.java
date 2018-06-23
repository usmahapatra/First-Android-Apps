package com.example.ujjwalsmahapatra.viewpagerswiping;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFrag extends Fragment {

    int[] images={R.drawable.electronicspng,R.drawable.grocerypng,R.drawable.homepng,R.drawable.kidspng,
            R.drawable.luggage,R.drawable.men,R.drawable.women,R.drawable.mobiles};



    public MyFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my, container, false);
        ImageView iv=v.findViewById(R.id.imageView);
        Bundle b=getArguments();
        int pos=b.getInt("pos");
        iv.setImageResource(images[pos]);
        return v;
    }

}
