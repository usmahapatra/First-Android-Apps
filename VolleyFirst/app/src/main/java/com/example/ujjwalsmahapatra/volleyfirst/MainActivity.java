package com.example.ujjwalsmahapatra.volleyfirst;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //url
    private static final String JSON_URL="https://api.github.com/users";

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MyAdapter adapter;

    List<Users> usersList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView1);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter=new MyAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //declare a method to fetch
        loadUsersList();
    }


    private void loadUsersList() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //get the whole json object from the response
                try {
                    JSONArray array=new JSONArray(response);
                    //get the id and login from response
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        Users users=new Users(object.getInt("id"),object.getString("login"));
                        adapter.usersArrayList.add(users);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }
}
