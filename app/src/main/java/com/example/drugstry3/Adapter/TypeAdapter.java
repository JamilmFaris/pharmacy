package com.example.drugstry3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.ClickListener.ClickListener;
import com.example.drugstry3.R;
import com.example.drugstry3.ViewHolder.TypeViewHolder;

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeViewHolder> {
    ArrayList<String> types = new ArrayList<>();
    ClickListener listener ;
    public TypeAdapter(ClickListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_holder,
                null);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        holder.bind(types.get(position));
        final int index = holder.getAbsoluteAdapterPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
    public void addItems(ArrayList<String> types){
        this.types.clear();
        this.types.addAll(types);
        notifyDataSetChanged();
    }
}
