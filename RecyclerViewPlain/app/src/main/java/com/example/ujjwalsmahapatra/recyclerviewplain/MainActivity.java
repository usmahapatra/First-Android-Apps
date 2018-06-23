package com.example.ujjwalsmahapatra.recyclerviewplain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList <String> cricketer;
    EditText editText1;
    Button button1;

    //RecyclerView:
    RecyclerView rv;
    LinearLayoutManager manager;

    //Adapter for the recyclerView:

    MyAdapter m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button) findViewById(R.id.button1);
        editText1=(EditText)findViewById(R.id.editText1);

        cricketer=new ArrayList<String>();

        rv=(RecyclerView)findViewById(R.id.recyclerview1);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        m=new MyAdapter();
        rv.setAdapter(m);
        rv.setLayoutManager(manager);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText1.getText().toString();
                cricketer.add(name);
                m.notifyDataSetChanged();
                editText1.setText("");
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
            holder.textView.setText(cricketer.get(position));
        }

        @Override
        public int getItemCount() {
            return cricketer.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.textView1);
        }
    }
}
