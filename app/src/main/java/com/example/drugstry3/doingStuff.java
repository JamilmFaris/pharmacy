package com.example.drugstry3;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.example.drugstry3.Adapter.FragmentAdapter;
import com.example.drugstry3.LanguageChange.LocaleHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Locale;

public class doingStuff extends FragmentActivity

{
    public static int NUM_PAGES = 4;
    public static ViewPager2 viewPager2;
    FragmentStateAdapter fragmentAdapter;
    public static int repositorySelected = -1;
    public static int productSelected = -1;
    public static int typeSelected = -1;
    public static int companySelected = -1;
    public static int repositorySelectedTimes = 0;
    public static int companySelectedTimes = 0;
    public static TabLayoutMediator tabLayoutMediator;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_stuff);

        ///init
        context = LocaleHelper.setLocale(doingStuff.this, "ar");
        resources = context.getResources();
        viewPager2 = findViewById(R.id.view_pager);
        fragmentAdapter = new FragmentAdapter(this);

        ///


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
                    tab.setText(resources.getString(R.string.repositories));
                }
                else if(position == 1){
                    if(companySelected != -1 )
                        tab.view.setClickable(true);
                    else
                        tab.view.setClickable(false);
                    tab.setText(resources.getString(R.string.companies));
                }
                else if(position == 2){

                    tab.setText(resources.getString(R.string.types));
                    if(typeSelected != -1)
                        tab.view.setClickable(true);
                    else
                        tab.view.setClickable(false);
                }
                else {
                    tab.setText(resources.getString(R.string.kinds));
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