package com.example.ujjwalsmahapatra.colourdrive;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

public class SecondActivity extends AppCompatActivity {


        TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
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
                    String userName=j.getString("username");
                    String password=j.getString("password");
                    String id=j.getString("id");
                    String name=j.getString("name");
                    String age=j.getString("age");
                    String city=j.getString("city");
                    String company=j.getString("company");

                    textView1.setText("Username :"+userName);
                    textView2.setText("Password :"+password);
                    textView3.setText("ID :"+id);
                    textView4.setText("Name :"+name);
                    textView5.setText("Age :"+age);
                    textView6.setText("City :"+city);
                    textView7.setText("Company :"+company);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView5=(TextView)findViewById(R.id.textView5);
        textView6=(TextView)findViewById(R.id.textView6);
        textView7=(TextView)findViewById(R.id.textView7);
        if(checkInternet()){
            MyTask m=new MyTask();
            String stringUrl="http://www.beta.colourdrive.in/apk/user_detail.php?username=harish&password=abc";
            m.execute(stringUrl);
        }
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
