package com.example.ujjwalsmahapatra.recyclerviewbeanclass;

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
    EditText editText1,editText2,editText3;
    Button button1;
    RecyclerView recyclerView1;
    //Source of Data:
    ArrayList<Employee> arrayList;

    // Recycler view needs two things: rv.setAdapter(AdapterObject) and rv.setLayoutManager(lyoutmgrObject)

    MyAdapter myAdapter;
    LinearLayoutManager linearLayoutMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        button1=(Button)findViewById(R.id.button1);
        recyclerView1=(RecyclerView)findViewById(R.id.recyclerview);

        arrayList=new ArrayList<Employee>();
        myAdapter=new MyAdapter();
        linearLayoutMgr=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView1.setAdapter(myAdapter);
        recyclerView1.setLayoutManager(linearLayoutMgr);
        //I forgot below code repeatedly:
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee emp=new Employee(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
                arrayList.add(emp);
                myAdapter.notifyDataSetChanged();
            }
        });


    }

    public class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder vh=new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Employee employee=arrayList.get(position);
            holder.t1.setText(employee.getName());
            holder.t2.setText(employee.getSalary());
            holder.t3.setText(employee.getDesignation());
        }

        @Override
        public int getItemCount() {
            //size of the source:
            return arrayList.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        public ViewHolder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.textView1);
            t2=(TextView)itemView.findViewById(R.id.textView2);
            t3=(TextView)itemView.findViewById(R.id.textView3);
        }
    }
}
