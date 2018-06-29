package com.example.ujjwalsmahapatra.openweatherapijsonparsing;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    Button button1;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;
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

                JSONArray weatherArray=j.getJSONArray("weather");
                JSONObject k=weatherArray.getJSONObject(0);
                String main = k.getString("main");

                textView1.setText("Main: "+main);
                textView2.setText("Description: "+k.getString("description"));
                JSONObject temp=j.getJSONObject("main");

                textView3.setText("Temperature: "+temp.getString("temp"));
                textView4.setText("Humidity"+temp.getString("humidity"));
                JSONObject ob2=j.getJSONObject("sys");
                textView5.setText("Country: "+ob2.getString("country"));
                textView6.setText("City"+j.getString("name"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.editTex1t);
        button1=(Button)findViewById(R.id.button1);
        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView5=(TextView)findViewById(R.id.textView5);
        textView6=(TextView)findViewById(R.id.textView6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkInternet()){
                    MyTask m=new MyTask();
                    String stringUrl="http://api.openweathermap.org/data/2.5/weather?zip="+editText1.getText().toString()+",in&appid=3d277f4e2f01297dccf143be29a14350";
                    m.execute(stringUrl);
                }
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
