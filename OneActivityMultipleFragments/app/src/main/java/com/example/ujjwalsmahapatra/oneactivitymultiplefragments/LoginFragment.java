package com.example.ujjwalsmahapatra.oneactivitymultiplefragments;


import android.content.SharedPreferences;
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
public class LoginFragment extends Fragment {
    EditText etUid,etPwd;
    Button buttonlog,buttonRegister;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);
        etPwd=(EditText)v.findViewById(R.id.editText2);
        etUid=(EditText)v.findViewById(R.id.editText1);
        buttonlog=(Button)v.findViewById(R.id.button1);
        buttonRegister=(Button)v.findViewById(R.id.button2);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity m= (MainActivity) getActivity();
                m.register();

            }
        });

        buttonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String uid=etUid.getText().toString();
                MainActivity m= (MainActivity) getActivity();
                m.receive(uid,etPwd.getText().toString());
            }
        });

        return v;
    }

}
