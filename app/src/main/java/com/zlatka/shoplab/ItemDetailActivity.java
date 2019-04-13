package com.zlatka.shoplab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemDetailActivity extends AppCompatActivity {

    public static final int ADD_PRODUCT_REQUEST_CODE = 123;

    public static final String TITLE_KEY = "title";
    public static final String DESCRIPTION_KEY = "description";
    public static final String AMOUNT_KEY = "amount";

    private EditText mEditTitle;
    private EditText mEditDescription;
    private EditText mEditAmount;

    private Button mCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mEditTitle = findViewById(R.id.et_product_title);
        mEditDescription = findViewById(R.id.et_edit_details);
        mEditAmount = findViewById(R.id.et_products_count);

        mCreateButton = findViewById(R.id.btn_create_product);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                String title = mEditTitle.getText().toString();
                String description = mEditDescription.getText().toString();
                int amount = Integer.parseInt(mEditAmount.getText().toString());
                i.putExtra(TITLE_KEY, title);
                i.putExtra(DESCRIPTION_KEY, description);
                i.putExtra(AMOUNT_KEY, amount);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });

    }
}
