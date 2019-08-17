package com.example.dell.navbot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter  extends FragmentPagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new mainFragment();
            case 1:
                return new profileFragment();
            case 2:
                return new notifyFragment();
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Main";
            case 1:
                return "profile";
            case 2:
                return "notify";
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}
