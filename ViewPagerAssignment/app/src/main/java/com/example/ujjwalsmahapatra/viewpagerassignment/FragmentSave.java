package com.example.ujjwalsmahapatra.viewpagerassignment;


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
public class FragmentSave extends Fragment {
    EditText et1,et2;
  //  Student s;
    Button b1;


    public FragmentSave() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_save, container, false);
        et1=v.findViewById(R.id.edittext1);
        et2=v.findViewById(R.id.edittext2);
        b1=v.findViewById(R.id.button1);
       // s=new Student(getActivity());
        //s.open();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1=et1.getText().toString();
                String subject1=et2.getText().toString();

                //call the database object which is created in the MainActivity:

                MainActivity m= (MainActivity) getActivity();
                m.db.insertstudent(name1,subject1);

                et1.setText("");
                et2.setText("");

                et1.requestFocus();
                Toast.makeText(m,"inserted..",Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

}
