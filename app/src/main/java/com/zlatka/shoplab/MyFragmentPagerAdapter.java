package com.zlatka.shoplab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zlatka.shoplab.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {


//    private Fragment[] mFragments;
//    List<Fragment> mFragments;
    private Context mContext;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
//        mFragments = new LinkedList<>();
//        mFragments.add(ProductsFragment.newInstance(ProductsFragment.RV_LINES));
//        mFragments.add(ProductsFragment.newInstance(ProductsFragment.RV_GRID));

//        mFragments = new Fragment[2];
        mContext = context;
    }


//    public List<Fragment> getFragments() {
//        return Arrays.asList(mFragments);
//    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return ProductsFragment.newInstance(ProductsFragment.RV_LINES);

                //                mFragments[0] = ProductsFragment.newInstance(ProductsFragment.RV_LINES);
//                return mFragments[0];
            case 1:
                return  ProductsFragment.newInstance(ProductsFragment.RV_GRID);
//                mFragments[1] =
//                return mFragments[1];
            default:
                throw new IllegalStateException("Index out of bounds of viewpager");
        }
    }

    @Override
    public int getCount() {
        return 2;
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
