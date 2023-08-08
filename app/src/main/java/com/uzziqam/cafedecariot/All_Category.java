package com.uzziqam.cafedecariot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uzziqam.cafedecariot.adapter.Categoryadapter;
import com.uzziqam.cafedecariot.helper.Category;

public class All_Category extends AppCompatActivity {
    RecyclerView menuRecyclerView;

    ImageButton btnBack,btncart;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference= database.getReference().child("category");
    Categoryadapter categoryadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);

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

        menuRecyclerView=findViewById(R.id.rvmennu);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

        FirebaseRecyclerOptions<Category> options=new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("category"),Category.class).build();

        categoryadapter=new Categoryadapter(options);
        menuRecyclerView.setAdapter(categoryadapter);

        categoryadapter.setOnItemClickListener(new Categoryadapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DataSnapshot dataSnapshot, int position) {
                String Id=dataSnapshot.getKey();
                Intent intent=new Intent(All_Category.this, All_Produts.class);
                intent.putExtra("key",Id);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        categoryadapter.startListening();
    }

}