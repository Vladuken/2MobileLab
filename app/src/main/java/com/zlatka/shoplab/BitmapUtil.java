package com.zlatka.shoplab;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.IOException;

public class BitmapUtil {

    public static void setImage(Context context, ImageView imageView, Uri imageUri,int width,int height){
        try{

            final Bitmap selectedImage = MediaStore.Images.Media.getBitmap(
                    context.getContentResolver(),imageUri);

            imageView.setImageBitmap(
                    ThumbnailUtils.extractThumbnail(
                            selectedImage,
                            width,height)
            );

        }catch (IOException e){
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_search_black_24dp,context.getTheme()));
        }
    }
}
