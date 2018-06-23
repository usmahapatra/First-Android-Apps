package com.example.ujjwalsmahapatra.dialogfirst;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProgressDialog extends DialogFragment
{
 public MyProgressDialog(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d=null;
        ProgressDialog p=new ProgressDialog(getActivity());
        p.setTitle("progress dialog");
        p.setIcon(R.mipmap.ic_launcher);
        p.setMessage("downloading wait...");
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

            super.onResume();
            p.setProgress(20);


        d=p;       //Important do not remove this line
        return d;//Important


    }
}
