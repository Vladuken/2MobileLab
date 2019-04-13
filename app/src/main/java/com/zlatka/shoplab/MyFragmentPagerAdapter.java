package com.zlatka.shoplab;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    List<Fragment> mFragments;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new LinkedList<>();
        mFragments.add(ProductsFragment.newInstance());
        mFragments.add(ProductsFragment.newInstance());
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Строки";

        }else {
            return "Столбцы";
        }
    }
}
