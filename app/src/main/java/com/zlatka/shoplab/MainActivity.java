package com.zlatka.shoplab;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;
import com.zlatka.shoplab.rv_products.ProductsAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private MyFragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.vpPager);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(mAdapter);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.app_menu, menu);
//
//        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search)
//                .getActionView();
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                useFilter(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                useFilter(s);
//                return false;
//            }
//
//            private void useFilter(String s){
//                for (Fragment fragment :mAdapter.getFragments()){
//                    if(fragment instanceof ProductsFragment){
//                        ProductsFragment productsFragment = (ProductsFragment) fragment;
//                        ProductsAdapter productsAdapter = productsFragment.getProductsAdapter();
//                        productsAdapter.getFilter().filter(s);
//                    }
//                }
//
//            }
//        });
//
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                return true;
            case R.id.app_bar_add:
                Intent i = new Intent(this, ItemCreateActivity.class);
                startActivityForResult(i, ItemCreateActivity.ADD_PRODUCT_REQUEST_CODE);
                return true;
            case R.id.app_bar_basket:
                Intent basketIntent = new Intent(this, BasketActivity.class);
                startActivityForResult(basketIntent,BasketActivity.UPDATE_ITEMS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == ItemCreateActivity.ADD_PRODUCT_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null){

            Bundle bundle = data.getExtras();
            Product product = new Product();
            try{
                product.title = bundle.getString(Constants.TITLE_KEY);
                product.description = bundle.getString(Constants.DESCRIPTION_KEY);
                product.amount = bundle.getInt(Constants.AMOUNT_KEY,0);
                product.image_uri = bundle.getString(Constants.IMAGE_URI_KEY);
                SingletonDatabase.getInstance(this).productDao().insertAll(product);
            }catch (NullPointerException e){
                Snackbar.make(mViewPager,getString(R.string.something_went_wrong),Snackbar.LENGTH_LONG).show();
            }

//            for (Fragment fragment:mAdapter.getFragments()){
//                fragment.onActivityResult(requestCode,resultCode,data);
//            }
        }

        if(requestCode == BasketActivity.UPDATE_ITEMS && resultCode == Activity.RESULT_OK){
//            for (Fragment fragment:mAdapter.getFragments()){
//                fragment.onActivityResult(requestCode,resultCode,data);
//            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
