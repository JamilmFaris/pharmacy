package com.example.drugstry3.Pages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.drugstry3.BottomNavigationPages.Shopping;
import com.example.drugstry3.ClickListener.ClickListener;
import com.example.drugstry3.LanguageChange.LocaleHelper;
import com.example.drugstry3.Model.Company;
import com.example.drugstry3.Model.Repository;
import com.example.drugstry3.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class Companies extends Fragment {
    ArrayList<Company>companies = new ArrayList<>();

    RecyclerView companiesRecyclerView;
    public static CompanyAdapter companyAdapter;
    Context context;
    Resources resources;
    public Companies() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        //init
        changeLanguage("en");
        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index) {
                Toast.makeText(getContext()
                        , "company " + companies.get(index).CoName + " chosen",
                        Toast.LENGTH_SHORT ).show();
                Shopping.companySelected = index;
                Shopping.tabLayoutMediator.detach();
                Shopping.tabLayoutMediator.attach();
                Shopping.companySelectedTimes++;
                Shopping.viewPager2.setCurrentItem(Shopping.viewPager2.getCurrentItem() +1);

            }
        };
        companyAdapter = new CompanyAdapter(listener);


        companiesRecyclerView = view.findViewById(R.id.companies_list);
        companiesRecyclerView.setHasFixedSize(true);
        companiesRecyclerView.setLayoutManager(new GridLayoutManager(Companies.this.getContext(),
                2, RecyclerView.VERTICAL, false));
        companiesRecyclerView.setAdapter(companyAdapter);

        //get data
        RequestQueue queue =  Volley.newRequestQueue(Companies.this.getContext());
        String url = "http://durgs.robotic-mind.com/WebService.asmx/Companies?level=SelectbyStore&CoId=&sid=";
        int repositorySelected = Shopping.repositorySelected + 1;
        url += repositorySelected;

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
    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(getContext(), language);
        resources = context.getResources();
    }
}