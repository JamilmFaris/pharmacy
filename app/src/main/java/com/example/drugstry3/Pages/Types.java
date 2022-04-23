package com.example.drugstry3.Pages;

import static com.example.drugstry3.Pages.Repositories.searchbar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drugstry3.Adapter.TypeAdapter;
import com.example.drugstry3.BottomNavigationPages.Shopping;
import com.example.drugstry3.ClickListener.RepositoryClickListener;
import com.example.drugstry3.ClickListener.TypeClickListener;
import com.example.drugstry3.LanguageChange.LocaleHelper;
import com.example.drugstry3.R;

import java.util.ArrayList;

public class Types extends Fragment {
    RecyclerView typesRecyclerView;
    TypeAdapter typeAdapter ;
    ArrayList<String> types = new ArrayList<>();
    Context context;
    Resources resources;

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
        //init
        changeLanguage("en");
        TypeClickListener listener = new TypeClickListener() {
            @Override
            public void click(int index) {
                Toast.makeText(getContext()
                        , "type " + types.get(index) + " chosen",
                        Toast.LENGTH_SHORT ).show();
                Shopping.typeSelected = index;
                Shopping.tabLayoutMediator.detach();
                Shopping.tabLayoutMediator.attach();
                searchbar.setHint(resources.getString(R.string.search_for_product));
                Shopping.viewPager2.setCurrentItem(Shopping.viewPager2.getCurrentItem() +1);
            }
        };
        typeAdapter = new TypeAdapter(listener);


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_types, container, false);
        types.add(resources.getString(R.string.normal));types.add(resources.getString(R.string.offer));
        typesRecyclerView = view.findViewById(R.id.types_list);
        typeAdapter.addItems(types);
        typesRecyclerView.setHasFixedSize(true);
        typesRecyclerView.setLayoutManager(new GridLayoutManager(Types.this.getContext(),
                2, RecyclerView.VERTICAL, false));
        typesRecyclerView.setAdapter(typeAdapter);
        return view;

    }
    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(getContext(), language);
        resources = context.getResources();
    }
}