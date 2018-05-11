package com.example.ujjwalsmahapatra.twodynamicfragmentsintwoactivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
        EditText editText1,editText2,editText3,editText4;
        Button button1;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        editText1=(EditText) v.findViewById(R.id.editText1);
        editText2=(EditText) v.findViewById(R.id.editText2);
        editText3=(EditText) v.findViewById(R.id.editText3);
        editText4=(EditText) v.findViewById(R.id.editText4);
        button1=(Button) v.findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity m=(MainActivity) getActivity();
                m.catchData(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());


            }
        });
        return v;
    }

}
