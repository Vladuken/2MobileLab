package com.zlatka.shoplab.rv_products;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlatka.shoplab.R;
import com.zlatka.shoplab.model.AppDatabase;
import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    List<Product> mProducts;
    AppDatabase mDatabase;

    public ProductsAdapter(AppDatabase database) {
        mDatabase = database;
        mProducts = mDatabase.productDao().getAll();
    }

    public void refreshAdapter(){
        mProducts = mDatabase.productDao().getAll();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item,viewGroup,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.bind(mProducts.get(i));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
