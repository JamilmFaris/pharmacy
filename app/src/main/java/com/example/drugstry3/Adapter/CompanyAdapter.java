package com.example.drugstry3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.Model.Company;
import com.example.drugstry3.R;
import com.example.drugstry3.ViewHolder.CompaniesViewHolder;

import java.util.ArrayList;

public class CompanyAdapter extends RecyclerView.Adapter<CompaniesViewHolder> {
    ArrayList<Company> companies = new ArrayList<>();
    @NonNull
    @Override
    public CompaniesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_holder, null);
        return new CompaniesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompaniesViewHolder holder, int position) {
        holder.bind(companies.get(position));
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }
    public void addItems(ArrayList<Company> companies){
        this.companies.clear();
        this.companies.addAll(companies);
        notifyDataSetChanged();
    }
}
