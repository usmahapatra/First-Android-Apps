package com.example.govi.a1106viepagerassignment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFrag extends Fragment {

Student s;
MyAdapter m;
//SQLiteDatabase sdb;
Cursor c;
LinearLayoutManager manager;
RecyclerView rv;
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder vh=new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

            c.moveToPosition(position);
            int sno=c.getInt(0);
            String name=c.getString(1);
            String subject=c.getString(2);

            holder.tv1.setText(""+sno);
            holder.tv2.setText(name);
            holder.tv3.setText(subject);

        }

        @Override
        public int getItemCount() {
            return c.getCount();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv1,tv2,tv3;
            public ViewHolder(View itemView) {
                super(itemView);

                tv1=itemView.findViewById(R.id.textview1);
                tv2=itemView.findViewById(R.id.textview2);
                tv3=itemView.findViewById(R.id.textview3);
            }
        }
    }
    public RecyclerFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_recycler, container, false);
        rv=v.findViewById(R.id.recyclerview1);
        s=new Student(getActivity());
        m=new MyAdapter();

        s.open();
        c=s.querystudent();


        rv.setAdapter(m);

        manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(manager);

        return v;
    }

}
