package com.example.ujjwalsmahapatra.databasefirst;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import static com.example.ujjwalsmahapatra.databasefirst.R.id.textView4;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    ListView listView;
    SimpleCursorAdapter sca;
    Button b1,b2;
    //Database

    MyDatabase m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText) findViewById(R.id.editText1);
        et2=(EditText) findViewById(R.id.editText2);
        listView=(ListView) findViewById(R.id.listview1);

        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
//create and open database:
        m=new MyDatabase(this);
        m.open();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.insertStudent(et1.getText().toString(),et2.getText().toString());

            }
        });

    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cursor c=m.queryStudent();
            /*StringBuilder sb=new StringBuilder();
            if(c!=null){
                while ((c.moveToNext()==true)){

                    int sno=c.getInt(0);
                    String sname= c.getString(1);
                    String sub=c.getString(2);
                    sb.append(sno+"-"+sname+"-"+sub+"\n");
                }



            }*/
            //cursor adapter logic:
            sca=new SimpleCursorAdapter(MainActivity.this,
                    R.layout.row,
                    c,
                    new String[]{"_id","sname","sub"},
                    new int[]{R.id.textView2,
                            R.id.textView3,
                            R.id.textView4});
            listView.setAdapter(sca);
        }
    });
    }

    protected void onDestroy() {

        super.onDestroy();
        if (m != null)
            m.close();


    }


}
