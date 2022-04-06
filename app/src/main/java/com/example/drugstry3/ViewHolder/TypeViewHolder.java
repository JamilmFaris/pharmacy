package com.example.drugstry3.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.Model.Company;
import com.example.drugstry3.R;

public class TypeViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public TypeViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.type_name);

    }
    public void bind(String type){
        textView.setText(type);
    }
}
