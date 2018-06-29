package com.example.ujjwalsmahapatra.getdatajsonfromhive;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    Button b1;

    MyAdapter m;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyTask myTask;
    ArrayList <MyContact> arrayList;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        arrayList=new ArrayList<MyContact>();
        b1=(Button) v.findViewById(R.id.button1);
        myTask=new MyTask();
        m=new MyAdapter();
        recyclerView=(RecyclerView) v.findViewById(R.id.recyclerView1);
        linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(m);
        recyclerView.setLayoutManager(linearLayoutManager);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInternet()){
                    Toast.makeText(getActivity(),"Internet Available",Toast.LENGTH_SHORT).show();
                    MyTask m=new MyTask();
                    m.execute("https://api.androidhive.info/contacts");
                    ((Button)view).setEnabled(false);//disable button
                }
                else {
                    Toast.makeText(getActivity(),"Internet Unavailable",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
public  boolean checkInternet()
{
    ConnectivityManager mgr= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo info=mgr.getActiveNetworkInfo();
    if(info!=null && info.isConnected())
    {
        return true;//means Internet connection is available
    }
    return false;//means there is no internet connection
}

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= getLayoutInflater().inflate(R.layout.row,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyContact m=arrayList.get(position);
        holder.tv1.setText(m.getSno()+"");
        holder.tv2.setText(m.getName());
        holder.tv3.setText(m.getMobile());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView) itemView.findViewById(R.id.textView1);
            tv2=(TextView) itemView.findViewById(R.id.textView2);
            tv3=(TextView) itemView.findViewById(R.id.textView3);
        }
    }


    public class MyTask extends AsyncTask<String,Void,StringBuilder>
    {
        URL myurl;
        HttpURLConnection con;
        InputStream i;
        InputStreamReader ir;
        BufferedReader br;
        StringBuilder sb;
        String s;

        @Override
        protected StringBuilder doInBackground(String... strings) {
            try {
                myurl=new URL(strings[0]);
                con= (HttpURLConnection) myurl.openConnection();
                i=con.getInputStream();
                ir=new InputStreamReader(i);

                br=new BufferedReader(ir);
                Log.d("input stream reader", String.valueOf(ir.read()));
                /****************************/
                sb=new StringBuilder();
                s=br.readLine();
                while (s!=null)
                {
                    sb.append(s);
                    s=br.readLine();
                }
                /*********************************/
                return sb;//now JSON data will go to post execute
            } catch (MalformedURLException e) {
                Log.d("B41","INVALID URL");
            } catch (IOException e) {
                Log.d("B41","INTERNET PROBLEM"+e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(StringBuilder stringBuilder) {
           // Toast.makeText(getActivity(),"result: "+stringBuilder,Toast.LENGTH_SHORT).show();
            try {
                JSONObject obj=new JSONObject(stringBuilder.toString());
                JSONArray arr=obj.getJSONArray("contacts");
                for(int i=0;i<arr.length();i++){
                    JSONObject temp=arr.getJSONObject(i);
                    String name=temp.getString("name");
                    JSONObject phone=temp.getJSONObject("phone");
                    String mobile=phone.getString("mobile");
                    Toast.makeText(getActivity(),name+"&"+mobile,Toast.LENGTH_SHORT).show();
                    MyContact x=new MyContact(i+1,name,mobile);
                    arrayList.add(x);
                    m.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                Log.d("abcerror","+e");
                Toast.makeText(getActivity(), "lol", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(stringBuilder);
        }
    }

}
