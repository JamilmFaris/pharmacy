package com.example.drugstry3.Pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.drugstry3.Adapter.ProductAdapter;
import com.example.drugstry3.Model.Product;
import com.example.drugstry3.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class Products extends Fragment {
    ArrayList<Product> products = new ArrayList<>();
    RecyclerView productsRecyclerView ;
    ProductAdapter productAdapter = new ProductAdapter();
    public Products() {
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
        View view =  inflater.inflate(R.layout.fragment_products, container, false);
        productsRecyclerView = view.findViewById(R.id.product_list);

        String url = "http://durgs.robotic-mind.com/WebService.asmx/Products?level=SelectbyComp&prodid=&coid=1&type=&str=All";
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0;i < response.length();i++){
                            try {
                                products.add(new Product(response.getJSONObject(i).getString("prodID"),
                                        response.getJSONObject(i).getString("prodName"),
                                        response.getJSONObject(i).getString("notes"),
                                        response.getJSONObject(i).getString("state"),
                                        response.getJSONObject(i).getString("priceSt"),
                                        response.getJSONObject(i).getString("img"),
                                        response.getJSONObject(i).getString("dateCreated"),
                                        response.getJSONObject(i).getString("price"),
                                        response.getJSONObject(i).getString("storeName"),
                                        response.getJSONObject(i).getString("companyName"),
                                        response.getJSONObject(i).getString("catName"),
                                        response.getJSONObject(i).getString("phPrice"),
                                        response.getJSONObject(i).getString("sPrice"),
                                        response.getJSONObject(i).getString("limit"),
                                        response.getJSONObject(i).getString("rate"),
                                        response.getJSONObject(i).getString("validity"),
                                        response.getJSONObject(i).getString("type"),
                                        response.getJSONObject(i).getString("sort"),
                                        response.getJSONObject(i).getString("fk_store"),
                                        response.getJSONObject(i).getString("year"),
                                        response.getJSONObject(i).getString("month"),
                                        response.getJSONObject(i).getString("day"))
                                );
                                Toast.makeText(getContext(),
                                        response.getJSONObject(0).getString("prodID"),
                                        Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        products.add(new Product("fd"));
                        productAdapter.addItems(products);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),
                                "Volley Error " + error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(jsonArrayRequest);

        productsRecyclerView.setLayoutManager(new GridLayoutManager(Products.this.getContext(),
                2, LinearLayoutManager.VERTICAL,false ));
        productsRecyclerView.setHasFixedSize(true);
        productsRecyclerView.setAdapter(productAdapter);
        return view;

    }
}