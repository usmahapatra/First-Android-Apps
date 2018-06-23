package com.example.ujjwalsmahapatra.dialogfirst;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar extends DialogFragment {
    public void Calender(){}
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d=null;
        java.util.Calendar c= java.util.Calendar.getInstance();
        int year=c.get(java.util.Calendar.YEAR);
        int month=c.get(java.util.Calendar.MONTH);
        int day=c.get(java.util.Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(getActivity(),"You Have Selected the Date: "+i2+"/"+(i1+1)+"/"+i,Toast.LENGTH_LONG).show();
            }
        }, year, month, day);//0 for january

        d=dpd;//  Important
        return d;// Important

    }
}
