package com.uzziqam.cafedecariot.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uzziqam.cafedecariot.All_Category;
import com.uzziqam.cafedecariot.All_Deals;
import com.uzziqam.cafedecariot.All_Produts;
import com.uzziqam.cafedecariot.Deal_Detail;
import com.uzziqam.cafedecariot.R;
import com.uzziqam.cafedecariot.adapter.Categoryadapter;
import com.uzziqam.cafedecariot.adapter.Dealsadapter;
import com.uzziqam.cafedecariot.helper.Category;
import com.uzziqam.cafedecariot.helper.Deals;


public class First extends Fragment {
    TextView cateMore,menuMore;
    RecyclerView menuRecyclerView,dealRecyclerView;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference= database.getReference().child("category");
    Categoryadapter categoryadapter;

    Dealsadapter dealsadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view=inflater.inflate(R.layout.fragment_first, container, false);

         cateMore=view.findViewById(R.id.cateview);
         menuMore=view.findViewById(R.id.dealViewMore);

         cateMore.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), All_Category.class);
                 startActivity(intent);
             }
         });

         menuMore.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), All_Deals.class);
                 startActivity(intent);
             }
         });

         menuRecyclerView=view.findViewById(R.id.rvmenu);

         dealRecyclerView=view.findViewById(R.id.rvdeals);

         menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true));

        FirebaseRecyclerOptions<Category> options=new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(databaseReference,Category.class).build();

        categoryadapter=new Categoryadapter(options);
        menuRecyclerView.setAdapter(categoryadapter);

        dealRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true));

        FirebaseRecyclerOptions<Deals> options1=new FirebaseRecyclerOptions.Builder<Deals>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("deal").limitToFirst(8), Deals.class).build();

        dealsadapter=new Dealsadapter(options1);
        dealRecyclerView.setAdapter(dealsadapter);

        categoryadapter.setOnItemClickListener(new Categoryadapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DataSnapshot dataSnapshot, int position) {
                String Id=dataSnapshot.getKey();
                Intent intent=new Intent(getContext(), All_Produts.class);
                intent.putExtra("key",Id);
                startActivity(intent);
            }
        });

        dealsadapter.setOnItemClickListener(new Dealsadapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DataSnapshot dataSnapshot, int position) {
                String Id=dataSnapshot.getKey();
                Intent intent=new Intent(getContext(), Deal_Detail.class);
                intent.putExtra("key",Id);
                startActivity(intent);
            }
        });

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        categoryadapter.startListening();
        dealsadapter.startListening();
    }

}