package com.example.ujjwalsmahapatra.loginfragmentswithdatabase;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {
    MyDatabase mdb;
    Cursor c;
    RecyclerView rv;
    LinearLayoutManager manager;
    MyAdapter1 m;

        public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder>
        {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v=getActivity().getLayoutInflater().inflate(R.layout.row,parent,false);
                ViewHolder vh=new ViewHolder(v);
                return vh;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                //The below line I have not written:
                c.moveToPosition(position);
                holder.tv1.setText(c.getInt(0)+"");
                holder.tv2.setText(c.getString(1));
                holder.tv3.setText(c.getString(2));
            }

            @Override
            public int getItemCount() {
                //Toast.makeText(getActivity(),c.getCount(),Toast.LENGTH_LONG).show();
                return c.getCount();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                public TextView tv1,tv2,tv3;
                public ViewHolder(View itemView) {
                    super(itemView);
                    tv1=(TextView) itemView.findViewById(R.id.textView1);
                    tv2=(TextView) itemView.findViewById(R.id.textView2);
                    tv3=(TextView) itemView.findViewById(R.id.textView3);
                }
            }
        }



    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_display, container, false);

        m=new MyAdapter1();
        mdb=new MyDatabase(getActivity());
        mdb.open();
        c=mdb.queryStudent();
        Toast.makeText(getActivity(),"hi.."+c.getCount(),Toast.LENGTH_LONG).show();

        rv=(RecyclerView) v.findViewById(R.id.recyclerView);


        //the below line I have not included:
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        rv.setAdapter(m);
        rv.setLayoutManager(manager);



        return v;
    }

}
