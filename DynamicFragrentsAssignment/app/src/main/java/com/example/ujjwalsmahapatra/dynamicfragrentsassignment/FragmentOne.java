package com.example.ujjwalsmahapatra.dynamicfragrentsassignment;


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
public class FragmentOne extends Fragment {
    TextView textView;
    EditText editText1;
    Button button;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
       textView=(TextView)v.findViewById(R.id.textView1);
       editText1=(EditText) v.findViewById(R.id.editText1);
       button=(Button)v.findViewById(R.id.button1);
        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            String name=bundle.getString("name");
            textView.setText(name);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qualification=editText1.getText().toString();
                MainActivity m= (MainActivity) getActivity();
                m.receive("Qualification:"+qualification);

            }
        });
       return v;
    }


}
