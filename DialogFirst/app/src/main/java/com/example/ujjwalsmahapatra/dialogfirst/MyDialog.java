package com.example.ujjwalsmahapatra.dialogfirst;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d=null;
        AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());
        ab.setTitle("First Pop Up");
        ab.setIcon(R.mipmap.ic_launcher);
        ab.setMessage("This is my First");
        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"Clicked OK",Toast.LENGTH_LONG).show();
            }
        });
        ab.setNegativeButton("CANCEL",null);
        d=ab.create();
        return d;
    }
}
