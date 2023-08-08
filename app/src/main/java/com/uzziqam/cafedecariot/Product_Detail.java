package com.uzziqam.cafedecariot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Product_Detail extends AppCompatActivity {
TextView tvproductName,tvTitle,tvpPrice,tvpDetail,tvvPrice,tvEPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        String pId= getIntent().getStringExtra("key").toString();

    }
}