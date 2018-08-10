package com.example.ujjwalsmahapatra.coworksfirstjson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;


    EditText editText1;
    Button button1;
    TextView textView1,textView2;
    MyTask myTask;
    public class MyTask extends AsyncTask<String,Void,StringBuilder>
    {
        URL myUrl;
        HttpURLConnection con;
        InputStream i;
        InputStreamReader ir;
        BufferedReader br;
        StringBuilder sb;
        String s;

        @Override
        protected StringBuilder doInBackground(String... strings) {
            try {
                myUrl=new URL(strings[0]);
                con= (HttpURLConnection) myUrl.openConnection();
                i=con.getInputStream();
                ir=new InputStreamReader(i);
                br=new BufferedReader(ir);
                sb=new StringBuilder();
                s=br.readLine();
                while (s!=null)
                {
                    sb.append(s);
                    s=br.readLine();
                }
                return sb;
            } catch (MalformedURLException e) {
                Log.d("B41","INVALID URL");
            } catch (IOException e) {
                Log.d("B41","Network Problem");
            }
            return null;
        }

        @Override
        protected void onPostExecute(StringBuilder stringBuilder) {
            super.onPostExecute(stringBuilder);
            try {
                JSONObject j=new JSONObject(stringBuilder.toString());


                JSONObject k=j.getJSONObject("main");
                String temp = k.getString("temp");
                String humidity=k.getString("humidity");

                textView1.setText("Temperature : "+temp);
                textView2.setText("Humidity : "+humidity);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.spinner);

        arrayList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayList);
        arrayList.add("--Please Select any City--");
        arrayList.add("London");
        arrayList.add("Bangalore");
        arrayList.add("Delhi");
        arrayList.add("Mumbai");
        arrayList.add("Kolkata");
        arrayList.add("Chennai");
        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        spinner.setAdapter(arrayAdapter);


               spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       Log.d("Hii",arrayList.get(i));
                       if(checkInternet()&&i!=0){
                           MyTask m=new MyTask();
                           String stringUrl="http://api.openweathermap.org/data/2.5/weather?q="+arrayList.get(i)+"&appid=d40a5c637b308e756bf904b26ecbd539";
                           m.execute(stringUrl);
                       }
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> adapterView) {

                   }
               });

    }

    public boolean checkInternet()
    {
        ConnectivityManager mgr= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=mgr.getActiveNetworkInfo();
        if(info!=null && info.isConnected())
        {
            return true;
        }
        else{
            return false;
        }

    }


}
