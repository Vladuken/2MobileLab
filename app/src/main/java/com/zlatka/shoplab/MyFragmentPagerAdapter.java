package com.zlatka.shoplab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zlatka.shoplab.model.Product;

import java.util.LinkedList;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    List<Fragment> mFragments;
    private Context mContext;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mFragments = new LinkedList<>();
        mFragments.add(ProductsFragment.newInstance(ProductsFragment.RV_LINES));
        mFragments.add(ProductsFragment.newInstance(ProductsFragment.RV_GRID));

        mContext = context;
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
            return mContext.getString(R.string.rows);

        }else {
            return mContext.getString(R.string.columns);
        }
    }
}
