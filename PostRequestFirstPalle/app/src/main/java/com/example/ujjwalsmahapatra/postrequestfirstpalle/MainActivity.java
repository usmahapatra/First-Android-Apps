package com.example.ujjwalsmahapatra.postrequestfirstpalle;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText et1,et2,et3;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button) findViewById(R.id.button);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        tv=(TextView)findViewById(R.id.tv);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask m=new MyTask();
                m.execute("http://hmkcode.appspot.com/jsonservlet");
            }
        });
    }
    public class MyTask extends AsyncTask<String,Void,Integer>
    {
        URL url;
        HttpURLConnection connection;
        OutputStream outputStream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        String name,city,twitter;

        @Override
        protected void onPostExecute(Integer integer) {
            switch(integer){
                case HttpURLConnection.HTTP_OK:
                    tv.setText("SUCCESS");
                    break;
                case -2:
                   tv.setText("Malformed");
                    break;
                case 0:
                    tv.setText("JSON Exception");
                    break;
                case -1:
                    tv.setText("IOException");
                    break;
                    default:tv.setText("Server response failure:"+integer);
                    break;

            }
        }

        @Override
        protected void onPreExecute() {
            name=et1.getText().toString();
            city=et2.getText().toString();
            twitter=et3.getText().toString();
        }

        @Override
        protected Integer doInBackground(String... strings) {
            try {
                url=new URL(strings[0]);
                connection= (HttpURLConnection) url.openConnection();
                //Below line tells the server that we need connection for the Post Request
                connection.setDoOutput(true);
                //we need to specifythe type of data being sent to server
                connection.setRequestProperty("Content-type","application/json");

                JSONObject j=new JSONObject();
                j.accumulate("name",name);
                j.accumulate("country",city);
                j.accumulate("twitter",twitter);

                outputStream=connection.getOutputStream();
                outputStreamWriter=new OutputStreamWriter(outputStream);

                outputStreamWriter.write(j.toString());

                outputStreamWriter.flush();

                int result=connection.getResponseCode();
                return result;
            } catch (MalformedURLException e) {
                return -2;
            } catch (IOException e) {
                return -1;
            } catch (JSONException e) {
                return 0;
            }

        }
    }

}
