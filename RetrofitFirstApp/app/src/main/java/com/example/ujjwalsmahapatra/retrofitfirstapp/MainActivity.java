package com.example.ujjwalsmahapatra.retrofitfirstapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.listView);
        this.getUsers();
    }

    public void getUsers(){
        //Create retrofit Object
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



                //create an api interface
        Api api=retrofit.create(Api.class);

        //make the ecall object
        Call<List<Users>> call=api.getUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> list=response.body();
                String[] users =new String[list.size()];

                //loop through all the users
                for(int i=0;i<list.size();i++)
                {
                    users[i]=list.get(i).getLogin();
                    Log.d("Running","Running onResponse");
                }

                //display the string array on recyclerview
                //set the adapter and layout manager here
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,users));
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

                Log.d("Failure","Failure");
                //Toast.makeText(MainActivity.this,"Something is Incorrect ",Toast.LENGTH_SHORT).show();
            }
        });

    }



}
