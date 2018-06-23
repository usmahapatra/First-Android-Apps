package com.example.ujjwalsmahapatra.miniprojectunderpalletechnologies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void receive(String uid, String pwd)
    {
        SharedPreferences sp=getSharedPreferences("credentials",0);
        String previousUid=sp.getString("uid",null);
        char p,u;
        boolean registered=true;
        if(uid.length()!=previousUid.length())registered=false;
        if(uid.length()==previousUid.length()) {
            for (int i = 0; i <uid.length();i++)
            {
                p=previousUid.charAt(i);
                u=uid.charAt(i);
                if(p!=u)
                {
                    registered=false;
                    break;
                }
            }
        }

        if(registered) {
            String getpwd=sp.getString("pwd",null);
            char pw,g;
            boolean pwdmatched=true;
            if(getpwd.length()!=pwd.length())pwdmatched=false;
            if(getpwd.length()==pwd.length())
            {
                for(int i=0;i<pwd.length();i++)
                {
                    g=getpwd.charAt(i);
                    pw=pwd.charAt(i);
                    if(g!=pw)
                    {
                        pwdmatched=false;
                        break;
                    }

                }
            }
            if(pwdmatched) {
                FragmentManager mgr = getSupportFragmentManager();
                FragmentTransaction trans = mgr.beginTransaction();
                FragmentWelcome welcomeFragment = new FragmentWelcome();
                trans.replace(R.id.container1, welcomeFragment);
                Bundle bundle = new Bundle();
                bundle.putString("uid", uid);
                welcomeFragment.setArguments(bundle);
                trans.addToBackStack(null);
                trans.commit();
            }
            else
                Toast.makeText(MainActivity.this,"The Password Entered is Wrong\n Please Enter the Correct Password",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"The User Id Entered is not Registered with us\n      Please Register",Toast.LENGTH_LONG).show();;
        }
    }

}
