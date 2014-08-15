package com.astuetz.example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.astuetz.example.CardFragment;


public class CustomPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;

    public CustomPagerAdapter(FragmentManager fragmentManager, int tabCount) {

        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @Override
    public int getCount() {

        return tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        return new CardFragment(++position);
    }

}