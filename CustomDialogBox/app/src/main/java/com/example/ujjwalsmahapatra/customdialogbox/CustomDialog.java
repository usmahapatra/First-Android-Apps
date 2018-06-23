package com.example.ujjwalsmahapatra.customdialogbox;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomDialog extends DialogFragment
{
    Button button1,button2;
    EditText editText1,editText2;
    Dialog d=null;


    public CustomDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());

        //load custom dialog xml
        View v= getActivity().getLayoutInflater().inflate(
                R.layout.fragment_custom_dialog,null);

        button1=(Button)v.findViewById(R.id.button4);
        button2=(Button)v.findViewById(R.id.button5);
        editText1=(EditText)v.findViewById(R.id.editText1);
        editText2=(EditText)v.findViewById(R.id.editText2);
        ab.setView(v);//ask alert dialog builder to load xml

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"...logout",Toast.LENGTH_SHORT).show();
                d.dismiss();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"login...",Toast.LENGTH_SHORT).show();
                d.dismiss();
            }
        });

        d=ab.create();//Important
        return d;
    }
}
