package com.example.ujjwalsmahapatra.mydatabasefirst;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button b1,b2;
    TextView t1,t2;

    Cursor c;
    MyDatabase m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText) findViewById(R.id.editText1);
        et2=(EditText) findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);


        m=new MyDatabase(this);

        m.open();
        c=m.query();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.insert(et1.getText().toString(),et2.getText().toString());
            }
        });

        c.move(-1);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.moveToNext();
                if(c.moveToLast()==true)
                    c.move(0);
                String name=c.getString(1);
                String sub=c.getString(2);
                t1.setText(name);
                t2.setText(sub);
            }
        });
    }

}
