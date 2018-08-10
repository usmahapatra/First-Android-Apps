package com.example.ujjwalsmahapatra.revisionrecyclerdatabase;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int pos=-1;
    EditText editText1,editText2;
    Button buttonSave,buttonEdit,buttonDelete;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyAdapter adapter;

    Cursor c;
    MyDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        buttonSave=(Button) findViewById(R.id.button1);
        buttonEdit=(Button) findViewById(R.id.button2);
        buttonDelete=(Button) findViewById(R.id.button3);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView1);


        myDatabase=new MyDatabase(this);
        myDatabase.open();

        c=myDatabase.queryContacts();

        adapter=new MyAdapter();
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText1.getText().toString();
                String number=editText2.getText().toString();

                myDatabase.insert(name,number);
                c=myDatabase.queryContacts();
                adapter.notifyDataSetChanged();
                editText1.setText("");editText2.setText("");
                editText1.requestFocus();
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos==-1){
                    Toast.makeText(MainActivity.this,"select an Item to delete",Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("editedit",pos+"");
                    myDatabase.update(pos,editText1.getText().toString(),editText2.getText().toString());
                    c=myDatabase.queryContacts();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("huhuhu",pos+"");
                if(pos==-1){
                    Toast.makeText(MainActivity.this,"select an Item to delete",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    myDatabase.delete(pos);

                    c=myDatabase.queryContacts();
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

        public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v=getLayoutInflater().inflate(R.layout.row,parent,false);
                ViewHolder vh=new ViewHolder(v);
                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                c.moveToNext();
                Log.d("zzzzz",position+"");
                holder.textView1.setText(""+c.getInt(0));
                holder.textView2.setText(c.getString(1));
                holder.textView3.setText(c.getString(2));
            }

            @Override
            public int getItemCount() {
                Log.d("hihihi",c.getCount()+"");
                return c.getCount();
            }
        }
            private class ViewHolder extends RecyclerView.ViewHolder {
                LinearLayout linearLayoutRow;
                TextView textView1,textView2,textView3;
                public ViewHolder(View itemView) {
                    super(itemView);
                    linearLayoutRow=(LinearLayout)itemView.findViewById(R.id.linearlayoutRow);
                    textView1=(TextView)itemView.findViewById(R.id.textView1);
                    textView2=(TextView)itemView.findViewById(R.id.textView2);
                    textView3=(TextView)itemView.findViewById(R.id.textView3);

                    linearLayoutRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            pos=getAdapterPosition();
                            c.moveToPosition(pos);
                            editText1.setText(c.getString(1));
                            editText2.setText(c.getString(2));
                            pos=c.getInt(0);

                        }
                    });
                }
            }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDatabase.close();
    }
}






