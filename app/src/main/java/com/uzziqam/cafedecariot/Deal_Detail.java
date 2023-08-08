package com.uzziqam.cafedecariot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Deal_Detail extends AppCompatActivity {
    TextView tvkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);

        tvkey=findViewById(R.id.idkey);

        String catId= getIntent().getStringExtra("key").toString();
        tvkey.setText(catId);
    }
}