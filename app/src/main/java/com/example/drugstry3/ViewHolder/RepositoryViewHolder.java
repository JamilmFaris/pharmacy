package com.example.drugstry3.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.Model.Company;
import com.example.drugstry3.Model.Repository;
import com.example.drugstry3.R;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public RepositoryViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.repository_name);
    }
    public void bind(Repository repository){
        textView.setText(repository.SName);
    }
}
