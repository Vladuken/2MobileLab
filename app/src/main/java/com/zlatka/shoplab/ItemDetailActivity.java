package com.zlatka.shoplab;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlatka.shoplab.model.Product;
import com.zlatka.shoplab.model.SingletonDatabase;

public class ItemDetailActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mDescription;
    private TextView mAmount;

    private Product mProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mImageView = findViewById(R.id.iv_product_photo);
        mTitle = findViewById(R.id.tv_detail_title);
        mDescription = findViewById(R.id.tv_details);
        mAmount = findViewById(R.id.tv_amount);

        int id = getIntent().getIntExtra(Constants.ID_KEY,1);
        mProduct = SingletonDatabase.getInstance(this).productDao().getById(id);

        mTitle.setText(mProduct.title);
        mDescription.setText(mProduct.description);
        mAmount.setText("" + mProduct.amount);



        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        BitmapUtil.setImage(this,mImageView, Uri.parse(mProduct.image_uri),
                width,
                Math.round(getResources().getDimension(R.dimen.list_item_image_height)));
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
