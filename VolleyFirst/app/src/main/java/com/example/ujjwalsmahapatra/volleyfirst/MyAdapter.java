package com.example.ujjwalsmahapatra.volleyfirst;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList <Users> usersArrayList;
    Activity activity;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(activity).inflate(R.layout.row,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.t1.setText(usersArrayList.get(position).getId()+"");
        holder.t2.setText(usersArrayList.get(position).getLogin());

    }

    @Override
    public int getItemCount() {

        return usersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        public ViewHolder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.text1);
            t2=(TextView)itemView.findViewById(R.id.text2);
        }
    }
}
