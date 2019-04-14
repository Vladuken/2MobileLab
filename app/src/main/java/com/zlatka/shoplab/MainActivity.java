package com.zlatka.shoplab;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private MyFragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.vpPager);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.app_bar_search:


                return true;
            case R.id.app_bar_add:
                Intent i = new Intent(this,ItemDetailActivity.class);
                startActivityForResult(i,ItemDetailActivity.ADD_PRODUCT_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}

        if(requestCode == ItemDetailActivity.ADD_PRODUCT_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            Product product = new Product();
            product.title = data.getStringExtra(ItemDetailActivity.TITLE_KEY);
            product.description = data.getStringExtra(ItemDetailActivity.DESCRIPTION_KEY);
            product.amount = data.getIntExtra(ItemDetailActivity.AMOUNT_KEY,0);
            product.image_uri = data.getStringExtra(ItemDetailActivity.IMAGE_URI_KEY);

            SingletonDatabase.getInstance(this).productDao().insertAll(product);

            for (Fragment fragment:mAdapter.mFragments){
                fragment.onActivityResult(requestCode,resultCode,data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
