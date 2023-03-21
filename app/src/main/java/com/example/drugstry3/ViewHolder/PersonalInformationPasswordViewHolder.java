package com.example.drugstry3.ViewHolder;

import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.R;

public class PersonalInformationPasswordViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public PersonalInformationPasswordViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.profile_password_text);
    }
    public void bind(String s){
        textView.setText(s);
        textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}
