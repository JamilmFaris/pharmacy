package com.example.drugstry3.Pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.drugstry3.Adapter.CompanyAdapter;
import com.example.drugstry3.Model.Company;
import com.example.drugstry3.Model.Repository;
import com.example.drugstry3.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class Companies extends Fragment {
    ArrayList<Company>companies = new ArrayList<>();

    RecyclerView companiesRecyclerView;
    CompanyAdapter companyAdapter = new CompanyAdapter();
    public Companies() {
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
        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        companiesRecyclerView = view.findViewById(R.id.companies_list);
        companiesRecyclerView.setHasFixedSize(true);
        companiesRecyclerView.setLayoutManager(new GridLayoutManager(Companies.this.getContext(),
                2, RecyclerView.VERTICAL, false));
        companiesRecyclerView.setAdapter(companyAdapter);

        //get data
        RequestQueue queue =  Volley.newRequestQueue(Companies.this.getContext());
        String url = "http://durgs.robotic-mind.com/WebService.asmx/Companies?level=SelectbyStore&CoId=&sid=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0;i < response.length();i++){
                            try {
                                companies.add(new Company(
                                        response.getJSONObject(i).getString("CoID"),
                                        response.getJSONObject(i).getString("CoName"),
                                        new Repository(response.getJSONObject(i).getString("StoreName")),
                                        response.getJSONObject(i).getString("address"),
                                        response.getJSONObject(i).getString("phone"),
                                        response.getJSONObject(i).getString("notes"),
                                        response.getJSONObject(i).getString("state"),
                                        response.getJSONObject(i).getString("img"),
                                        response.getJSONObject(i).getString("dateCreated"),
                                        response.getJSONObject(i).getString("fk_store")
                                ));

                                companyAdapter.addItems(companies);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Companies.this.getContext(), "VolleyError " + error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(jsonArrayRequest);
        return view;
    }
}