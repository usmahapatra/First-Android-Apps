package com.example.ujjwalsmahapatra.fragmentofsecondactivitytofirstactivityfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    EditText editText;
    Button button;
    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_two, container, false);
        button=(Button)v.findViewById(R.id.button);
        editText=(EditText)v.findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= editText.getText().toString();
                Second sec=(Second)getActivity();
                sec.catchData(text);
            }
        });
        return v;
    }

}
