package com.example.ujjwalsmahapatra.dialogfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void clicked(View view)
    {
        switch (view.getId())
        {
            case  R.id.button1:
                MyDialog m=new MyDialog();
                m.show(getSupportFragmentManager(),null);
                break;

            case R.id.button2:
            MyProgressDialog myProgressDialog=new MyProgressDialog();
            myProgressDialog.show(getSupportFragmentManager(),null);
            break;


            case  R.id.button3:
                Calendar md=new Calendar();
                md.show(getSupportFragmentManager(),null);
                break;

            case R.id.button4:
                MyTimeDialog tmd=new MyTimeDialog();
                tmd.show(getSupportFragmentManager(),null);
                break;

        }

    }
}
