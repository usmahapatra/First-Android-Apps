package com.example.ujjwalsmahapatra.oneactivitymultiplefragments;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager mgr =getSupportFragmentManager();
        FragmentTransaction transaction=mgr.beginTransaction();
        LoginFragment lf=new LoginFragment();
        transaction.add(R.id.container1,lf);
        transaction.commit();
    }
    public void register()
    {
        FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction trans=mgr.beginTransaction();
        RegisterFragment rf=new RegisterFragment();
        trans.replace(R.id.container1,rf);
        trans.addToBackStack(null);
        trans.commit();
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
                WelcomeFragment welcomeFragment = new WelcomeFragment();
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
