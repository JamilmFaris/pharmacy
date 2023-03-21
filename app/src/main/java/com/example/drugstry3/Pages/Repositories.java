package com.example.drugstry3.Pages;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.drugstry3.Adapter.RepositoryAdapter;
import com.example.drugstry3.BottomNavigationPages.Shopping;
import com.example.drugstry3.ClickListener.RepositoryClickListener;
import com.example.drugstry3.LanguageChange.LocaleHelper;
import com.example.drugstry3.MainActivity;
import com.example.drugstry3.Model.Repository;
import com.example.drugstry3.R;
import com.google.android.material.navigation.NavigationView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repositories extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener
{

    RecyclerView recyclerView;
    RepositoryAdapter adapter;
    RepositoryClickListener listener;
    RequestQueue queue;
    public ArrayList<Repository> repositories = new ArrayList<>();
    Context context;
    Resources resources;
    public static EditText searchbar;
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

        changeLanguage(MainActivity.language);
        queue = Volley.newRequestQueue(this.getContext());
        listener = new RepositoryClickListener() {
            @Override
            public void click(int index) {
                /*Toast.makeText(getContext()
                        , "repository " + repositories.get(index).SName + " chosen",
                       Toast.LENGTH_SHORT ).show();*/
                Shopping.repositorySelected = index;
                Shopping.repositorySelectedTimes++;
                Shopping.tabLayoutMediator.detach();
                Shopping.tabLayoutMediator.attach();
                searchbar.setHint(resources.getString(R.string.search_for_companies));
                Shopping.viewPager2.setCurrentItem(Shopping.viewPager2.getCurrentItem() +1);
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
        //queue.add(jsonArrayRequest);
        /// testing
        repositories.add(new Repository("jamil"));repositories.add(new Repository("jamil"));
        repositories.add(new Repository("jamil"));repositories.add(new Repository("jamil"));
        adapter.addItems(repositories);
        ///
        recyclerView = view.findViewById(R.id.repositories_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),
                2, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            if(searchbar == null){
                //Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();
            }
            // filter customization
            searchbar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!searchbar.getText().toString().isEmpty()){
                        String key = searchbar.getText().toString();
                        ArrayList<Repository> filtered = (ArrayList<Repository>) filter(key);
                        adapter.addItems(filtered);
                    }
                    else{
                        adapter.addItems(repositories);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                    //
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(getContext(), language);
        resources = context.getResources();
    }
    public List<Repository> filter(String key){
        List<Repository> res = new ArrayList<>();
        for(int i = 0;i < repositories.size();i++){
            String curName = repositories.get(i).SName;
            boolean include = true;
            for(int j = 0;j < key.length();j++){
                if(!curName.contains( key.charAt(j) + "" ) ){
                    include = false;
                    break;
                }
            }
            if(include){
                res.add(repositories.get(i));
            }
        }
        return res;
    }
}