package com.example.ujjwalsmahapatra.firstserverprojectpalle;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    //create one inner class for AsyncTask:
    public class Mytask extends AsyncTask<String,Void,StringBuilder>{
        URL myurl;
        HttpURLConnection con;
        InputStream i;
        InputStreamReader ir;
        BufferedReader br;
        String s;
        StringBuilder sb;
        @Override
        protected StringBuilder doInBackground(String... strings) {
            try {
                myurl=new URL(strings[0]);
                con= (HttpURLConnection) myurl.openConnection();//opens the connection
                i=con.getInputStream();//channel for data in flow
                ir=new InputStreamReader(i);
                br=new BufferedReader(ir);
                /**********************************/
                sb=new StringBuilder();
                s=br.readLine();
                while (s!=null){
                    sb.append(s);
                    s=br.readLine();
                }
                return sb;
            } catch (MalformedURLException e) {
                Log.d("B41", "URL IS WRONG");
            }
            catch (IOException e1) {
               e1.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(StringBuilder stringBuilder) {
            if(stringBuilder==null){
                tv.setText("some thing went wrong- check log");
            }
            else{
                tv.setText(stringBuilder.toString());
            }
            super.onPostExecute(stringBuilder);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.textView1);

    }

    public boolean checkInternet()
    {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null && info.isConnected()==true){
            return true;// internet is available
        }
        else {
            return false;// there is no internet
        }
    }
    public void clicked(View view) {
        if(checkInternet()){
            Toast.makeText(this,"internet Available",Toast.LENGTH_SHORT).show();
            Mytask m=new Mytask();
            m.execute("http://techpalle.com");
        }
        else {
            Toast.makeText(this,"no internet",Toast.LENGTH_SHORT).show();
        }
    }
}
