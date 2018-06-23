package com.example.ujjwalsmahapatra.oneactivitymultiplefragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    EditText editText1,editText2,editText3,editText4;
    Button button1;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        editText1=(EditText)v.findViewById(R.id.editText1);
        editText2=(EditText)v.findViewById(R.id.editText2);
        editText3=(EditText)v.findViewById(R.id.editText3);
        editText4=(EditText)v.findViewById(R.id.editText4);
        button1=(Button)v.findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = editText1.getText().toString();
                String pwd = editText2.getText().toString();
                String cnfpwd = editText3.getText().toString();
                String phnNo = editText4.getText().toString();
                char p, cp, u, guid;
                boolean match = true;
                boolean uidexists = true;
                SharedPreferences s = getActivity().getSharedPreferences("credentials", 0);
                String getuid = s.getString("uid", null);
                if(getuid==null)getuid="iiii";

                if (pwd.isEmpty())
                    match = false;
                if(uid.length()!=getuid.length())uidexists=false;
                if(uid.length()==getuid.length())
                {
                    for (int i = 0; i < uid.length(); i++) {
                        //uid exists:
                        u = uid.charAt(i);
                        guid = getuid.charAt(i);
                        if (u != guid) {
                            uidexists = false;
                            break;
                        }
                    }
                }
                if (uidexists) {
                    Toast.makeText(getActivity(), "User id Already Exists", Toast.LENGTH_LONG).show();
                } else {
                    if(pwd.length()!=cnfpwd.length())match=false;
                    if(pwd.length()==cnfpwd.length()){
                    for (int i = 0; i < pwd.length(); i++) {
                        //if pwd and cnf pwd do not match:
                        p = pwd.charAt(i);
                        cp = cnfpwd.charAt(i);
                        if (p != cp) {
                            match = false;


                            break;
                        }
                    }
                }
                    if (match) {
                        SharedPreferences sp = getActivity().getSharedPreferences("credentials", 0);
                        SharedPreferences.Editor et = sp.edit();
                        et.putString("uid", uid);
                        et.putString("pwd", pwd);
                        et.putString("cnfpwd", cnfpwd);
                        et.putString("phn", phnNo);
                        et.apply();
                    }
                    else Toast.makeText(getActivity(), "password field and confirm password field do not match", Toast.LENGTH_LONG).show();


                }
            }
        });
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText1.requestFocus();
        return v;
    }

}
