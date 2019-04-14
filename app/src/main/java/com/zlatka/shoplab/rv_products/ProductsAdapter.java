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

    public ProductsAdapter(List<Product> products) {
        mProducts =  products;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    //    public void refreshAdapter(){
//        mProducts = mDatabase.productDao().getAll();
//    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item,viewGroup,false);
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
//                i.putExtra(Constants.DESCRIPTION_KEY, product.description);
//                i.putExtra(Constants.AMOUNT_KEY, product.amount);
//                i.putExtra(Constants.IMAGE_URI_KEY,product.image_uri);

                v.getContext().startActivity(i);
                //TODO put into bundle
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
