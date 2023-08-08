package com.uzziqam.cafedecariot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uzziqam.cafedecariot.adapter.Dealsadapter;
import com.uzziqam.cafedecariot.adapter.ProductAdapter;
import com.uzziqam.cafedecariot.helper.Category;
import com.uzziqam.cafedecariot.helper.Deals;
import com.uzziqam.cafedecariot.helper.Products;

public class All_Produts extends AppCompatActivity {
    RecyclerView productRecyclerView;
    ProductAdapter productAdapter;
    ImageButton btnBack,btncart;
    TextView tvTitle;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    Query databaseReference,getCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_produts);

        String catId= getIntent().getStringExtra("key").toString();

        getCategory=database.getReference().child("category").child(catId);
        databaseReference=database.getReference().child("product").orderByChild("cid").equalTo(catId);



        tvTitle=findViewById(R.id.idtitle);

        getCategory.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Category category=snapshot.getValue(Category.class);
                tvTitle.setText(category.name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnBack=findViewById(R.id.idback);
        btncart=findViewById(R.id.idcart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        productRecyclerView=findViewById(R.id.rvproduct);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        FirebaseRecyclerOptions<Products> options=new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(databaseReference, Products.class).build();

        productAdapter=new ProductAdapter(options);
        productRecyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DataSnapshot dataSnapshot, int position) {
                String Id=dataSnapshot.getKey();
                Intent intent=new Intent(All_Produts.this, Product_Detail.class);
                intent.putExtra("key",Id);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        productAdapter.startListening();
    }
}