package com.example.ujjwalsmahapatra.fragmentswipeviewpager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;

public class PagerAdapter extends FragmentPagerAdapter{


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("position : ",position+"");
        switch (position){
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentTwo();
            case 2:
                return new FragmentThree();

                default:
                    return new FragmentTwo();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
