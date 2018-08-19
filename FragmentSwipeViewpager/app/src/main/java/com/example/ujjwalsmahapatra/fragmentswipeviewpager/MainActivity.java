package com.example.ujjwalsmahapatra.fragmentswipeviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpager;
    PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewPager1);
        adapter=new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }
}
