package com.zlatka.shoplab;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class BitmapUtil {

    public static void setImage(Context context, ImageView imageView, Uri imageUri,int width,int height){
//        try{

//            final Bitmap selectedImage = MediaStore.Images.Media.getBitmap(
//                    context.getContentResolver(),imageUri);
//
//            imageView.setImageBitmap(
//                    ThumbnailUtils.extractThumbnail(
//                            selectedImage,
//                            width,height)
//            );


            Picasso.get()
                    .load(imageUri)
                    .resize(width,height)
                    .centerCrop()
                    .placeholder(R.drawable.gray_background)
                    .error(R.drawable.gray_background)
                    .into(imageView);

//            Picasso.get()
//                    .load(url)
//                    .resize(50, 50)
//                    .centerCrop()
//                    .into(imageView)

//        }catch (IOException e){
//            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_search_black_24dp,context.getTheme()));
//        }
    }
}
