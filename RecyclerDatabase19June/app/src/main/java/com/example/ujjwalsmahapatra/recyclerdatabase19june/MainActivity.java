package com.example.ujjwalsmahapatra.recyclerdatabase19june;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button button,button2;

    RecyclerView recyclerView1;
    MyAdapter adapter1;
    LinearLayoutManager linearLayoutManager;

    Cursor cursor;
    MyDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder viewHolder=new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1=(TextView) itemView.findViewById(R.id.textView1);
            textView2=(TextView) itemView.findViewById(R.id.textView2);
            textView3=(TextView) itemView.findViewById(R.id.textView3);
        }
    }
}
