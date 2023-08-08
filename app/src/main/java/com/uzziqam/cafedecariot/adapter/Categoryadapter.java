package com.uzziqam.cafedecariot.adapter;

import android.content.ClipData;
import android.content.Intent;
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
import com.google.firebase.database.FirebaseDatabase;
import com.uzziqam.cafedecariot.All_Produts;
import com.uzziqam.cafedecariot.R;
import com.uzziqam.cafedecariot.helper.Category;

public class Categoryadapter extends FirebaseRecyclerAdapter<Category,Categoryadapter.CategoryViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private OnItemClickListener listener;
    public Categoryadapter(@NonNull FirebaseRecyclerOptions<Category> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Categoryadapter.CategoryViewHolder holder, int position, @NonNull Category model) {
        holder.textView.setText(model.getName());
        Glide.with(holder.imageView.getContext()).load(model.getImg()).into(holder.imageView);

    }

    @NonNull
    @Override
    public Categoryadapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);

        return new CategoryViewHolder(view);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cateImg);
            textView=itemView.findViewById(R.id.cateTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION&&listener!=null){
                        listener.OnItemClick(getSnapshots().getSnapshot(position),position );
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(DataSnapshot dataSnapshot,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
           this.listener=listener;
    }
}
