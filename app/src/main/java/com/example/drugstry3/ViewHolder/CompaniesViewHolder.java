package com.example.drugstry3.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.Model.Company;
import com.example.drugstry3.R;

public class CompaniesViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public CompaniesViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.company_name);
    }
    public void bind(Company company){
        textView.setText(company.CoName);
    }
}
