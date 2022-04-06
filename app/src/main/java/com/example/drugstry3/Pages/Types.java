package com.example.drugstry3.Pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drugstry3.Adapter.TypeAdapter;
import com.example.drugstry3.R;

import java.util.ArrayList;

public class Types extends Fragment {
    RecyclerView typesRecyclerView;
    TypeAdapter typeAdapter = new TypeAdapter();
    ArrayList<String> types = new ArrayList<>();

    public Types() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_types, container, false);
        types.add("normal");types.add("offer");
        typesRecyclerView = view.findViewById(R.id.types_list);
        typeAdapter.addItems(types);
        typesRecyclerView.setHasFixedSize(true);
        typesRecyclerView.setLayoutManager(new GridLayoutManager(Types.this.getContext(),
                2, RecyclerView.VERTICAL, false));
        typesRecyclerView.setAdapter(typeAdapter);
        return view;

    }
}