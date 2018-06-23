package com.example.ujjwalsmahapatra.loginfragmentswithdatabase;


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
    EditText etName,etSub;
    Button buttonInsert,buttonShow;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_login, container, false);
      etName=(EditText)v.findViewById(R.id.editText1);
      etSub=(EditText)v.findViewById(R.id.editText2);
      buttonInsert=(Button)v.findViewById(R.id.button1);
      buttonShow=(Button)v.findViewById(R.id.button2);

      buttonInsert.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              MainActivity m= (MainActivity) getActivity();
              m.receiveStudentDetails(etName.getText().toString(),etSub.getText().toString());
          }
      });

      buttonShow.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              MainActivity mainActivity= (MainActivity) getActivity();
                mainActivity.show();
          }
      });


        return v;
    }

}
