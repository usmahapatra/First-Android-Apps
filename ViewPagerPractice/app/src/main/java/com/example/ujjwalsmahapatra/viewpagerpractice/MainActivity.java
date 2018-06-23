package com.example.ujjwalsmahapatra.viewpagerpractice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    ViewPager v;
    MyAdapter m;
    TabLayout tabLayout;

    public class MyAdapter extends FragmentStatePagerAdapter {
        //Enter Number of Tabs:WRonG..
        //Enter the title of all the tabs:
        String [] tabs={"Time-ApplyToTheJobs","Time-StudyJava","Time-StudyAndroid"};

        @Override
        //to display title in tab:
        public CharSequence getPageTitle(int position) {
           return tabs[position];
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        FragmentTime ft=new FragmentTime();
        Bundle b=new Bundle();
        b.putInt("position",position);
        ft.setArguments(b);
        return ft;
        }

        @Override
        public int getCount() {
            return 3;//Number of Pages to Show
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize all the variables:
        v=findViewById(R.id.viewPager);
        m=new MyAdapter(getSupportFragmentManager());
        tabLayout=(TabLayout) findViewById(R.id.tablayout);

        //setadapter:

        v.setAdapter(m);

        tabLayout.setupWithViewPager(v);

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
