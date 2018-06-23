package com.example.ujjwalsmahapatra.dialogfirst;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Time;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTimeDialog extends DialogFragment {


    public MyTimeDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d=null;
       Calendar t= Calendar.getInstance();
        int hr=t.get(Calendar.HOUR_OF_DAY);
        int min=t.get(Calendar.MINUTE);

        TimePickerDialog tpd=new TimePickerDialog(getActivity(),null,hr,min,false);

        d=tpd;
        return d;
    }
}
