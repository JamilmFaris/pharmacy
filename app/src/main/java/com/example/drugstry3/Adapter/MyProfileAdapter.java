package com.example.drugstry3.Adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.Model.Bookmark;
import com.example.drugstry3.R;
import com.example.drugstry3.ViewHolder.PersonalInformationPasswordViewHolder;
import com.example.drugstry3.ViewHolder.PersonalInformationViewHolder;
import com.example.drugstry3.ViewHolder.PersonalTextViewHolder;

import java.util.ArrayList;

public class MyProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Pair< Bookmark, String>>  data = new ArrayList<>();

    private Integer personalText = 0;
    private Integer information = 1;
    private Integer password = 2;

    public MyProfileAdapter(Context myContext,ArrayList< Pair< Bookmark, String> >  data1){
        this.context = myContext;
        this.data.addAll(data1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == personalText){
            view = LayoutInflater.from(context).inflate(R.layout.myprofile_text_viewholder, parent, false);
            return new PersonalTextViewHolder(view);
        }
        else if(viewType == information){
            view = LayoutInflater.from(context).inflate(R.layout.myprofile_information_viewholder, parent, false);
            return new PersonalInformationViewHolder(view);
        }
        else{
            view = LayoutInflater.from(context).inflate(R.layout.myprofile_password_holder, parent, false);
            return new PersonalInformationPasswordViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bookmark d = data.get(position).first;
        if(d.getType().equals(personalText)){
            ((PersonalTextViewHolder) holder).bind(data.get(position).second);
        }
        else if(d.getType().equals(information)){
            ((PersonalInformationViewHolder) holder).bind(data.get(position).second);
        }
        else if(d.getType().equals(password)){
            ((PersonalInformationPasswordViewHolder) holder).bind(data.get(position).second);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        // based on you list you will return the ViewType
        return data.get(position).first.getType();
    }
    //TODO : add addItems function
}
