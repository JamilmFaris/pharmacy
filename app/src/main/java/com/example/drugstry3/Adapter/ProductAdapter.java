package com.example.drugstry3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.ClickListener.ProductClickListener;
import com.example.drugstry3.ClickListener.RepositoryClickListener;
import com.example.drugstry3.Model.Product;
import com.example.drugstry3.R;
import com.example.drugstry3.ViewHolder.ProductViewHolder;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    ArrayList<Product> products = new ArrayList();
    ProductClickListener listener;

    public ProductAdapter(ProductClickListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_holder,
                null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
        int index = holder.getAbsoluteAdapterPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public void addItems(ArrayList<Product> products){
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }
}
