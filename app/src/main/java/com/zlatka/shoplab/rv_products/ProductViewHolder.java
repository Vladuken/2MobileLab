package com.zlatka.shoplab.rv_products;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlatka.shoplab.R;
import com.zlatka.shoplab.model.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mDesciption;
    private TextView mAmount;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.imageView);
        mTitle = itemView.findViewById(R.id.tv_title);
        mDesciption = itemView.findViewById(R.id.tv_description);
        mAmount = itemView.findViewById(R.id.tv_amount);

//        mTextView = itemView.findViewById(R.id.);
    }

    public void bind(Product product){


        final Uri imageUri = Uri.parse(product.image_uri);

        try{

            final Bitmap selectedImage = MediaStore.Images.Media.getBitmap(
                    itemView.getContext().getContentResolver(),
                    imageUri);

            mImageView.setImageBitmap(
                    ThumbnailUtils.extractThumbnail(
                            selectedImage,
                            Math.round(itemView.getResources().getDimension(R.dimen.list_item_image_width)),
                            Math.round(itemView.getResources().getDimension(R.dimen.list_item_image_height))
                    )
            );

        }catch (IOException e){
            mImageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_search_black_24dp,itemView.getContext().getTheme()));
        }

        mTitle.setText(product.title);
        mDesciption.setText(product.description);
        mAmount.setText("" + product.amount);

//        mTextView.setText(product.toString());
    }
}
