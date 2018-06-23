package com.example.ujjwalsmahapatra.recyclerviewwithdatabasefirst;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;

    Button button,button2;
    RecyclerView rv;
    MyDatabase mdb;
    Cursor c;// in place of ArrayList
    MyAdapter m;
    //LinearLayoutManager manager;//RecyclerView requires this
    GridLayoutManager manager;
    int sno;//back up

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.editText1);
        et2= (EditText) findViewById(R.id.editText2);
        rv=(RecyclerView) findViewById(R.id.recyclerView);

        //database opens here:

        mdb=new MyDatabase(this);
        mdb.open();

        //read rows from student table - to cursor -> c

        c=mdb.queryStudent();
        //create the adapter and establish links:
         m=new MyAdapter();
         //manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        manager=new GridLayoutManager(this,1);
         rv.setAdapter(m);
         rv.setLayoutManager(manager);//link bw recycler view & layout mgr

        button=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button1);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname=et1.getText().toString().trim();
                String sub =et2.getText().toString().trim();
                mdb.insertStudent(sname,sub);//inserted a row
                m.notifyDataSetChanged();
                c=mdb.queryStudent();
                Toast.makeText(MainActivity.this,"inserted a row...",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");

                et1.requestFocus();


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname=et1.getText().toString().trim();
                String sub =et2.getText().toString().trim();
                mdb.updateStudent(sno,sname,sub);//inserted a row
                c=mdb.queryStudent();
                m.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"inserted a row...",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et1.requestFocus();


            }
        });

    }

    public class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder vh=new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            c.moveToPosition(position);
            holder.tv1.setText(""+c.getInt(0));
            holder.tv2.setText(c.getString(1));
            holder.tv3.setText(c.getString(2));

        }

        @Override
        public int getItemCount() {
            return c.getCount();
        }
    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1,tv2,tv3;
        public CardView cv1;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView) itemView.findViewById(R.id.textView1);
            tv2=(TextView) itemView.findViewById(R.id.textView2);
            tv3=(TextView) itemView.findViewById(R.id.textView3);
            cv1=itemView.findViewById(R.id.cv1);

            cv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    c.moveToPosition(pos);
                    int sno=c.getInt(0);//get num
                    MainActivity.this.sno = sno;
                    String sname=c.getString(1);
                    String sub=c.getString(2);
                    et1.setText(sname);
                    et2.setText(sub);
                    Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_LONG).show();
                }
            });

        }
    }



}
