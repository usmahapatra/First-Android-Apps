package com.example.ujjwalsmahapatra.viewpagerassignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    ViewPager v;
    MyAdapter1 myAdapter1;
    Student db;
    TabLayout t;

    public  class MyAdapter1 extends FragmentStatePagerAdapter {

        String[] tabs={"save","show"};

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return tabs[position];
        }

        public MyAdapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0)
            {
                FragmentSave fsave=new FragmentSave();
                return  fsave;
            }else
            {
                myAdapter1.notifyDataSetChanged();
                FragmentList flist=new FragmentList();
                return flist;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize all the variables
        v= (ViewPager) findViewById(R.id.viewpager1);
        myAdapter1=new MyAdapter1(getSupportFragmentManager());

        //Database

        db=new Student(this);
        db.open();

        //establish the link between view pager and adapter
        v.setAdapter(myAdapter1);

        //material design codes
        t= (TabLayout) findViewById(R.id.tablayout1);

        //link tabs with view pager
        t.setupWithViewPager(v);



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

}