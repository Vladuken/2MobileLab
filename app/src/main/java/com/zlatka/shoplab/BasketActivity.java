package com.zlatka.shoplab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;
import com.zlatka.shoplab.rv_products.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasketActivity extends AppCompatActivity {

    public static final int UPDATE_ITEMS = 2222;

    private ProductsAdapter mProductsAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_products);

        mRecyclerView = findViewById(R.id.rv_products);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products = SingletonDatabase.getInstance(this)
                .productInBasketDao().getAllProducts();

//        List<Product> productList = SingletonDatabase.getInstance(this).productDao().getAll();
        mProductsAdapter = new ProductsAdapter(products,R.layout.product_list_item);
        mRecyclerView.setAdapter(mProductsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basket_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.app_bar_clear_basket:
                clearBasket();
                return true;
            case R.id.app_bar_basket_buy:
                List<Product> products = SingletonDatabase.getInstance(this).productDao().getAll();
                List<Product> basketProducts = SingletonDatabase.getInstance(this).productInBasketDao().getAllProducts();

                //filter products
                products = products.stream()
                        .filter(prod -> basketProducts.stream()
                            .anyMatch(bProd -> prod.id == bProd.id)
                        )
                        .collect(Collectors.toList());

                //Deleta amount from list
                for(Product p:products){
                    for (Product bp:basketProducts){
                        if(p.id == bp.id){
                            p.amount = p.amount - bp.amount;
                        }
                    }
                }

                //update products
                for(Product p:products){
                    SingletonDatabase.getInstance(this).productDao().update(p);
                }

                clearBasket();

                setResult(Activity.RESULT_OK);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearBasket(){
        SingletonDatabase.getInstance(this).productInBasketDao().deleteAll();
        mProductsAdapter.getProducts().clear();
        mProductsAdapter.notifyDataSetChanged();
    }
}
