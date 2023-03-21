package com.example.drugstry3.ViewHolder;

import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.Model.Personal;
import com.example.drugstry3.R;

public class PersonalInformationViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public PersonalInformationViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.profile_personal_text_item);
    }
    public void bind(String s){
        textView.setText(s);
    }
}
