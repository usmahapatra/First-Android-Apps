package com.example.ujjwalsmahapatra.twofragmentsinoneactivity;


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

    EditText editText1,editText2;
    Button button;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        editText1=(EditText)v.findViewById(R.id.editText1);
        editText2=(EditText)v.findViewById(R.id.editText2);
        button=(Button) v.findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText1.getText().toString();
                MainActivity m=(MainActivity) getActivity();
                m.catchData(name);
            }
        });
        return v;
    }

}
