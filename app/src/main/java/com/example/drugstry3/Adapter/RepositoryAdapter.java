package com.example.drugstry3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.ClickListener.ClickListener;
import com.example.drugstry3.Model.Company;
import com.example.drugstry3.Model.Repository;
import com.example.drugstry3.R;
import com.example.drugstry3.ViewHolder.RepositoryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {

    ArrayList<Repository> repositories = new ArrayList<>();
    public ClickListener clickListener;
    public RepositoryAdapter(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_holder
                , parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        holder.bind(  repositories.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAbsoluteAdapterPosition();
                clickListener.click(index);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void addItems(ArrayList<Repository> repositories){
        this.repositories.clear();
        this.repositories.addAll(repositories);
        notifyDataSetChanged();
    }
}
