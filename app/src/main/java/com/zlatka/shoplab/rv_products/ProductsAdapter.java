package com.zlatka.shoplab.rv_products;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlatka.shoplab.Constants;
import com.zlatka.shoplab.ItemDetailActivity;
import com.zlatka.shoplab.R;
import com.zlatka.shoplab.model.AppDatabase;
import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    List<Product> mProducts;
    private int mLayoutId;

    public ProductsAdapter(List<Product> products,int layout_id) {
        mProducts =  products;
        mLayoutId = layout_id;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(mLayoutId,viewGroup,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        final Product product = mProducts.get(i);
        productViewHolder.bind(product);
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ItemDetailActivity.class);
                i.putExtra(Constants.ID_KEY, product.id);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
