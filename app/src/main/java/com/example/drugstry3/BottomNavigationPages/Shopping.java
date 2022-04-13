package com.example.drugstry3.BottomNavigationPages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drugstry3.Adapter.FragmentAdapter;
import com.example.drugstry3.R;
import com.example.drugstry3.doingStuff;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Shopping extends Fragment {

    public static ViewPager2 viewPager2;
    public static int NUM_PAGES = 4;
    public static int repositorySelected = -1;
    public static int productSelected = -1;
    public static int typeSelected = -1;
    public static int companySelected = -1;
    public static int repositorySelectedTimes = 0;
    public static int companySelectedTimes = 0;
    public static TabLayoutMediator tabLayoutMediator;

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
        viewPager2 = view.findViewById(R.id.view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity());
        viewPager2.setAdapter(fragmentAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(repositorySelectedTimes > 1 ){
                    fragmentAdapter.notifyItemChanged(1);
                }
                if( companySelectedTimes > 1){
                    fragmentAdapter.notifyItemChanged(3);
                }
            }
        });
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayoutMediator = new TabLayoutMediator(
                tabLayout
                , viewPager2
                , new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    if(repositorySelected != -1)
                        tab.view.setClickable(true);
                    else
                        tab.view.setClickable(false);
                    tab.setText(doingStuff.resources.getString(R.string.repositories));
                }
                else if(position == 1){
                    if(companySelected != -1 )
                        tab.view.setClickable(true);
                    else
                        tab.view.setClickable(false);
                    tab.setText(doingStuff.resources.getString(R.string.companies));
                }
                else if(position == 2){

                    tab.setText(doingStuff.resources.getString(R.string.types));
                    if(typeSelected != -1)
                        tab.view.setClickable(true);
                    else
                        tab.view.setClickable(false);
                }
                else {
                    tab.setText(doingStuff.resources.getString(R.string.kinds));
                    if(typeSelected != -1)
                        tab.view.setClickable(true);
                    else
                        tab.view.setClickable(false);
                }
            }

        }
        );
        tabLayoutMediator.attach();
        viewPager2.setUserInputEnabled(false);

        return view;
    }
}