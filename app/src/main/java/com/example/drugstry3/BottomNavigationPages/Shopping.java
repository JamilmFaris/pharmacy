package com.example.drugstry3.BottomNavigationPages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugstry3.Adapter.FragmentAdapter;
import com.example.drugstry3.LanguageChange.LocaleHelper;
import com.example.drugstry3.LoginAcitivity;
import com.example.drugstry3.Pages.Repositories;
import com.example.drugstry3.R;
import com.example.drugstry3.doingStuff;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Shopping extends Fragment {
    Context context;
    Resources resources;
    TextView hello ;

    public static ViewPager2 viewPager2;
    public static int NUM_PAGES = 4;
    public static int repositorySelected = -1;
    public static int productSelected = -1;
    public static int typeSelected = -1;
    public static int companySelected = -1;
    public static int repositorySelectedTimes = 0;
    public static int companySelectedTimes = 0;
    public static TabLayoutMediator tabLayoutMediator;
    public static TabLayout tabLayout;

    public Shopping() {
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
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Repositories.searchbar = view.findViewById(R.id.search_bar);

        changeLanguage("en");

        hello = view.findViewById(R.id.hello);
        String helloString = resources.getString(R.string.hello);
        helloString = helloString + " user";
        hello.setText(helloString);

        viewPager2 = view.findViewById(R.id.view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity());
        viewPager2.setAdapter(fragmentAdapter);

        tabLayout = view.findViewById(R.id.tabs);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                if(repositorySelectedTimes > 1 && viewPager2.getCurrentItem() == 1){
                    fragmentAdapter.notifyItemChanged(1);
                }
                if( companySelectedTimes > 1 && viewPager2.getCurrentItem() == 3){
                    fragmentAdapter.notifyItemChanged(3);
                }
                if(position == 0){
                    repositorySelected = -1;
                    companySelected = -1;
                    typeSelected = -1;
                    productSelected = -1;
//                    Toast.makeText(getContext(),
//                            "rep sel " + repositorySelected
//                                    + "\ncom sel " + companySelected
//                                    + "\nIm on pos 0"
//                            , Toast.LENGTH_SHORT).show();
                }
                else if(position == 1){
                    companySelected = -1;
                    typeSelected = -1;
                    productSelected = -1;
//                    Toast.makeText(getContext(),
//                            "rep sel " + repositorySelected
//                                    + "\ncom sel " + companySelected
//                                    + "\nIm on pos 1"
//                            , Toast.LENGTH_SHORT).show();
                }
                else if(position == 2){

                    typeSelected = -1;
                    productSelected = -1;
//                    Toast.makeText(getContext(),
//                            "rep sel " + repositorySelected
//                                    + "\ncom sel " + companySelected
//                            , Toast.LENGTH_SHORT).show();
                }
                else{
                    productSelected = -1;
//                    Toast.makeText(getContext(),
//                            "rep sel " + repositorySelected
//                                    + "\ncom sel " + companySelected
//                                    + "\nIm on pos 3"
//                            , Toast.LENGTH_SHORT).show();
                }
                refreshTab();
                super.onPageSelected(position);
            }
        }

        );

        tabLayoutMediator = new TabLayoutMediator(
                tabLayout
                , viewPager2
                , new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText(doingStuff.resources.getString(R.string.repositories));
                }
                else if(position == 1){
                    tab.setText(doingStuff.resources.getString(R.string.companies));
                }
                else if(position == 2){
                    tab.setText(doingStuff.resources.getString(R.string.types));
                }
                else if(position == 3) {
                    tab.setText(doingStuff.resources.getString(R.string.kinds));
                }
            }

        });
        tabLayoutMediator.attach();
        viewPager2.setUserInputEnabled(false);
    }
    public static void refreshTab(){
        if(repositorySelected == -1){
            for(int i = 0;i <= 0;i++){
                tabLayout.getTabAt(i).view.setClickable(true);
            }
            for(int i = 1;i <= 3;i++){
                tabLayout.getTabAt(i).view.setClickable(false);
            }
        }
        else if(companySelected == -1){
            for(int i = 0;i <= 1;i++){
                tabLayout.getTabAt(i).view.setClickable(true);
            }
            for(int i = 2;i <= 3;i++){
                tabLayout.getTabAt(i).view.setClickable(false);
            }
        }
        else if(typeSelected == -1){
            for(int i = 0;i <= 2;i++){
                tabLayout.getTabAt(i).view.setClickable(true);
            }
            for(int i = 3;i <= 3;i++){
                tabLayout.getTabAt(i).view.setClickable(false);
            }
        }
        else if(productSelected == -1){
            for(int i = 0;i <= 3;i++){
                tabLayout.getTabAt(i).view.setClickable(true);
            }
        }
    }
    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(getContext(), language);
        resources = context.getResources();
    }
}