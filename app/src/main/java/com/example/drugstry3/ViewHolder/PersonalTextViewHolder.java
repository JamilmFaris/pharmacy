package com.example.drugstry3.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.R;

public class PersonalTextViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public PersonalTextViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.personal_text);
    }
    public void bind(String s){
        textView.setText(s);
    }

}
