package com.zlatka.shoplab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;
import com.zlatka.shoplab.rv_products.ProductsAdapter;

import java.util.List;

public class ProductsFragment extends Fragment {

    private ProductsAdapter mProductsAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_products,container,false);
        mRecyclerView = v.findViewById(R.id.rv_products);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Product> products = SingletonDatabase.getInstance(getContext()).productDao().getAll();
        mProductsAdapter = new ProductsAdapter(products);
        mRecyclerView.setAdapter(mProductsAdapter);
        return v;
    }



    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == ItemCreateActivity.ADD_PRODUCT_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            List<Product> products = SingletonDatabase.getInstance(getContext()).productDao().getAll();
//        mProductsAdapter = new ProductsAdapter(products);
//        mRecyclerView.setAdapter(mProductsAdapter);
            mProductsAdapter.setProducts(products);
            mProductsAdapter.notifyItemInserted(mProductsAdapter.getItemCount());
        }

        if(requestCode == BasketActivity.UPDATE_ITEMS && resultCode == Activity.RESULT_OK){
            List<Product> products = SingletonDatabase.getInstance(getContext()).productDao().getAll();
            mProductsAdapter.setProducts(products);
            mProductsAdapter.notifyDataSetChanged();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
