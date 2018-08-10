package com.example.ujjwalsmahapatra.colourdrive;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button button1;
    CheckBox checkBox;
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
                String userNAme=j.getString("username");
                String passWord=j.getString("password");
                Log.d("username",userNAme);
                Log.d("password",passWord);
                boolean registered=true;
                String uid=editText1.getText().toString();
                if(uid.length()!=userNAme.length())registered=false;
                for (int i = 0; i <uid.length();i++) {
                    char p = userNAme.charAt(i);
                    char u = uid.charAt(i);
                    if (p != u) {
                        registered = false;
                        break;
                    }
                }
                if(registered) {
                    String getpwd=editText2.getText().toString();
                    char pw,g;
                    boolean pwdmatched=true;
                    if(getpwd.length()!=passWord.length())pwdmatched=false;
                    if(getpwd.length()==passWord.length())
                    {
                        for(int i=0;i<passWord.length();i++)
                        {
                            g=getpwd.charAt(i);
                            pw=passWord.charAt(i);
                            if(g!=pw)
                            {
                                pwdmatched=false;
                                break;
                            }

                        }
                    }
                    if(pwdmatched) {
                        Intent in = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(in);
                    }
                    else
                        Toast.makeText(MainActivity.this,"The Password Entered is Wrong\n Please Enter the Correct Password",Toast.LENGTH_LONG).show();
                    editText2.setError("Entered Password is Incorrect");
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                    editText1.setError("The Username Entered Does Not Exist");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.editText1);
        button1=(Button)findViewById(R.id.button);
        editText2=(EditText)findViewById(R.id.editText2);
        checkBox=(CheckBox) findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox.isChecked())
                {
                    editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {
                    Log.d("unchecked","unchecked");
                    editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1.getText().length()==0)
                {
                    editText1.setError("Field cannot be left blank");
                }
                else if(editText2.getText().length()==0)
                {
                    editText2.setError("Field cannot be left blank");
                }
                else {
                    if (checkInternet()) {
                        MyTask m = new MyTask();
                        String stringUrl = "http://www.beta.colourdrive.in/apk/user_detail.php?username=$username&password=$password";
                        m.execute(stringUrl);
                    }
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
