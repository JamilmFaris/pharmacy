package com.example.drugstry3.Pages;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.drugstry3.Adapter.RepositoryAdapter;
import com.example.drugstry3.ClickListener.ClickListener;
import com.example.drugstry3.Model.Repository;
import com.example.drugstry3.R;
import com.example.drugstry3.doingStuff;
import com.google.android.material.navigation.NavigationView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
public class Repositories extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener
{

    RecyclerView recyclerView;
    RepositoryAdapter adapter;
    ClickListener listener;
    RequestQueue queue;
    public ArrayList<Repository> repositories = new ArrayList<>();
    public Repositories() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //init
        queue = Volley.newRequestQueue(this.getContext());
        listener = new ClickListener() {
            @Override
            public void click(int index) {
                Toast.makeText(getContext()
                        , "repository " + repositories.get(index).SName + " chosen",
                       Toast.LENGTH_SHORT ).show();
                doingStuff.repositorySelected = index;
                doingStuff.tabLayoutMediator.detach();
                doingStuff.tabLayoutMediator.attach();
                doingStuff.repositorySelectedTimes++;
                doingStuff.viewPager2.setCurrentItem(doingStuff.viewPager2.getCurrentItem() +1);

            }
        };
        adapter = new RepositoryAdapter(listener);


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repositories, container, false);

        //get the repositories

        String url = "http://durgs.robotic-mind.com/WebService.asmx/Store?level=select&sid=&str=All";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0;i < response.length();i++){
                            try {
                                repositories.add(new Repository(response.getJSONObject(i).getString("SID"),
                                        response.getJSONObject(i).getString("SName"),
                                        response.getJSONObject(i).getString("address"),
                                        response.getJSONObject(i).getString("phone"),
                                        response.getJSONObject(i).getString("phone2"),
                                        response.getJSONObject(i).getString("catCount"),
                                        response.getJSONObject(i).getString("prodCount"),
                                        response.getJSONObject(i).getString("notes"),
                                        response.getJSONObject(i).getString("state"),
                                        response.getJSONObject(i).getString("image"),
                                        response.getJSONObject(i).getString("password"),
                                        response.getJSONObject(i).getString("cityName"),
                                        response.getJSONObject(i).getString("dateCreated"),
                                        response.getJSONObject(i).getString("fk_city"),
                                        response.getJSONObject(i).getString("rate")
                                        ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.addItems(repositories);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Repositories.this.getContext()
                                , "volleyError"  + error.toString()
                                , Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(jsonArrayRequest);

        recyclerView = view.findViewById(R.id.repositories_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),
                2, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}