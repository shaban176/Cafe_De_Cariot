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
import com.uzziqam.cafedecariot.helper.Products;

public class ProductAdapter extends FirebaseRecyclerAdapter<Products,ProductAdapter.ProductViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private ProductAdapter.OnItemClickListener listener;
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position, @NonNull Products model) {
        holder.tvtitle.setText(model.getPtitle());
        Glide.with(holder.imageView.getContext()).load(model.getImg()).into(holder.imageView);
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvtitle;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.pImg);
            tvtitle=itemView.findViewById(R.id.ptitle);

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
    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener){
        this.listener=listener;
    }
}
