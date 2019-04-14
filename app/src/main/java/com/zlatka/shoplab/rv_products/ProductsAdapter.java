package com.zlatka.shoplab.rv_products;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.zlatka.shoplab.Constants;
import com.zlatka.shoplab.ItemDetailActivity;
import com.zlatka.shoplab.R;
import com.zlatka.shoplab.model.AppDatabase;
import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder>
implements Filterable {

    List<Product> mProducts;
    private int mLayoutId;

    private List<Product> mProductsFiltered;

    public ProductsAdapter(List<Product> products,int layout_id) {
        mProducts =  products;
        mProductsFiltered = products;
        mLayoutId = layout_id;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
        mProductsFiltered = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(mLayoutId,viewGroup,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        final Product product = mProductsFiltered.get(i);
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
        return mProductsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    mProductsFiltered = mProducts;
                } else {
                    List<Product> filteredList = new ArrayList<>();
                    for (Product product : mProducts) {

                        if(product.title.toLowerCase().contains(charSequence) || product.description.toLowerCase().contains(charSequence)){
                            filteredList.add(product);
                        }
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        //if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
                        //    filteredList.add(row);
                        //}
                    }

                    mProductsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mProductsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mProductsFiltered = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

