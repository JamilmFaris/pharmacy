package com.example.drugstry3;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.media.AudioMetadataReadMap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.drugstry3.Adapter.FragmentAdapter;
import com.example.drugstry3.ClickListener.ClickListener;
import com.example.drugstry3.Pages.Repositories;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class doingStuff extends FragmentActivity

{
    public static int NUM_PAGES = 4;
    public static ViewPager2 viewPager2;
    FragmentStateAdapter fragmentAdapter;
    public static int repositorySelected = -1;
    public static TabLayoutMediator tabLayoutMediator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_stuff);

        ///init
        viewPager2 = findViewById(R.id.view_pager);
        fragmentAdapter = new FragmentAdapter(this);

        ///

        viewPager2.setAdapter(fragmentAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
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
                    tab.setText("Repositories");
                }
                else if(position == 1){
                    if(repositorySelected != -1)
                        tab.view.setClickable(true);
                    else
                        tab.view.setClickable(false);
                    tab.setText("Companies");
                }
                else if(position == 2){
                    tab.setText("Types");
                    tab.view.setClickable(false);
                }
                else {
                    tab.setText("Kinds");
                    tab.view.setClickable(false);
                }


            }

        }
        );
        tabLayoutMediator.attach();

        //viewPager2.setUserInputEnabled(false);

    }




    @Override
    public void onBackPressed() {
        if(viewPager2.getCurrentItem() == 0){
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        }
        else{
            // Otherwise, select the previous step.
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1);
        }
    }
}