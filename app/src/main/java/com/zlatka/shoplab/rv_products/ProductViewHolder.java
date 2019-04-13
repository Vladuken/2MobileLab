package com.zlatka.shoplab.rv_products;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zlatka.shoplab.R;
import com.zlatka.shoplab.model.Product;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.textView);
    }

    public void bind(Product product){
        mTextView.setText(product.toString());
    }
}
