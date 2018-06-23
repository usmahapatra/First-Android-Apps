package com.example.ujjwalsmahapatra.miniprojectunderpalletechnologies;


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
public class FragmentLogin extends Fragment {
    EditText editText1,editText2,editText3;
    Button button1,button2;



    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_login, container, false);
        editText1=(EditText)v.findViewById(R.id.editText1);
        editText2=(EditText)v.findViewById(R.id.editText2);
        editText3=(EditText)v.findViewById(R.id.editText3);
        button1=(Button)v.findViewById(R.id.button1);
        button2=(Button)v.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=editText1.getText().toString();
                MainActivity m= (MainActivity) getActivity();
                m.receive(uid,editText2.getText().toString());
            }
        });
        return v;
    }

}
