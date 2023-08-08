package com.uzziqam.cafedecariot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uzziqam.cafedecariot.adapter.Dealsadapter;
import com.uzziqam.cafedecariot.helper.Deals;

public class All_Deals extends AppCompatActivity {
    RecyclerView dealRecyclerView;
    Dealsadapter dealsadapter;
    ImageButton btnBack,btncart;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference= database.getReference().child("deal");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_deals);

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

        dealRecyclerView=findViewById(R.id.rvdeals);

        dealRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

        FirebaseRecyclerOptions<Deals> options1=new FirebaseRecyclerOptions.Builder<Deals>()
                .setQuery(databaseReference, Deals.class).build();

        dealsadapter=new Dealsadapter(options1);
        dealRecyclerView.setAdapter(dealsadapter);

        dealsadapter.setOnItemClickListener(new Dealsadapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DataSnapshot dataSnapshot, int position) {
                String Id=dataSnapshot.getKey();
                Intent intent=new Intent(All_Deals.this, Deal_Detail.class);
                intent.putExtra("key",Id);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        dealsadapter.startListening();
    }
}