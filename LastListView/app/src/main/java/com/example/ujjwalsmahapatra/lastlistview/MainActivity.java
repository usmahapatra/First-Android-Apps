package com.example.ujjwalsmahapatra.lastlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2,editText3,editText4;
    Button button1;
    //ListView listView;
    RecyclerView recyclerView;
    ArrayList<Movie> arrayList;
    LinearLayoutManager linearLayoutManager;
    MyAdapter m;

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> //public class MyAdapter extends BaseAdapter{
    {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder vh=new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Movie m=arrayList.get(position);
            int sno=m.getSno();
            String actor=m.getActor();
            String actress=m.getActress();
            String movieName=m.getMovieName();
            //I have done a mistake here that I have not converted the sno into string format while using that in setText method:
            holder.tv1.setText(sno+"");
            holder.tv2.setText(actor);
            holder.tv3.setText(actress);
            holder.tv4.setText(movieName);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            //
            public  TextView tv1,tv2,tv3,tv4;
            public ViewHolder(View itemView) {
                super(itemView);
                tv1=(TextView) itemView.findViewById(R.id.textView1);
                tv2=(TextView) itemView.findViewById(R.id.textView2);
                tv3=(TextView) itemView.findViewById(R.id.textView3);
                tv4=(TextView) itemView.findViewById(R.id.textView4);

            }
        }
      /*  @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

     */

       /* @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Movie mov= arrayList.get(i);
            int sno=mov.getSno();
            String actor=mov.getActor();
            String actress=mov.getActress();
            String movieName=mov.getMovieName();
            View v=View.inflate(MainActivity.this,R.layout.row,null);

            TextView textView1=(TextView) v.findViewById(R.id.textView1);
            Log.d("called","called once");
            TextView textView2=(TextView) v.findViewById(R.id.textView2);
            TextView textView3=(TextView) v.findViewById(R.id.textView3);
            TextView textView4=(TextView) v.findViewById(R.id.textView4);

            textView1.setText(""+sno);
            textView2.setText(movieName);
            textView3.setText(actor);
            textView4.setText(actress);

            return v;
        }*/


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        editText3=(EditText) findViewById(R.id.editText3);
        editText4=(EditText) findViewById(R.id.editText4);
        button1=(Button) findViewById(R.id.button1);
        //listView=(ListView)findViewById(R.id.listview1);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview1);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        m=new MyAdapter();
        arrayList=new ArrayList<Movie>();
        //listView.setAdapter(m);

        //
        recyclerView.setAdapter(m);
        recyclerView.setLayoutManager(linearLayoutManager);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieName=editText2.getText().toString();
                String actor=editText3.getText().toString();
                String actress=editText4.getText().toString();
                        int sno=Integer.parseInt(editText1.getText().toString());

                        Movie movie=new Movie(sno,movieName,actor,actress);
                        arrayList.add(movie);
                        m.notifyDataSetChanged();
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText1.requestFocus();
            }
        });
    }
}
