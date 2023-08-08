package com.uzziqam.cafedecariot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.uzziqam.cafedecariot.R;
import com.uzziqam.cafedecariot.helper.Deals;

public class Dealsadapter extends FirebaseRecyclerAdapter<Deals,Dealsadapter.DealsViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Dealsadapter.OnItemClickListener listener;
    public Dealsadapter(@NonNull FirebaseRecyclerOptions<Deals> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Dealsadapter.DealsViewHolder holder, int position, @NonNull Deals model) {

        Glide.with(holder.imageView.getContext()).load(model.getImg()).into(holder.imageView);
    }

    @NonNull
    @Override
    public Dealsadapter.DealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_item,parent,false);

        return new Dealsadapter.DealsViewHolder(view);
    }

    public class DealsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public DealsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.dealImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getPosition();
                    if (position!=RecyclerView.NO_POSITION&&listener!=null){
                        listener.OnItemClick(getSnapshots().getSnapshot(position),position );
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(DataSnapshot dataSnapshot, int position);
    }
    public void setOnItemClickListener(Dealsadapter.OnItemClickListener listener){
        this.listener=listener;
    }
}
