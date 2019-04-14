package com.zlatka.shoplab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;
import com.zlatka.shoplab.rv_products.ProductsAdapter;

import java.util.List;

public class BasketActivity extends AppCompatActivity {


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
        mProductsAdapter = new ProductsAdapter(products);
        mRecyclerView.setAdapter(mProductsAdapter);
    }
}
