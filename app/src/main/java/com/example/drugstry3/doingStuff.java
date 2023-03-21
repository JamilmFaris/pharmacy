package com.example.drugstry3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.drugstry3.BottomNavigationPages.Invoices;
import com.example.drugstry3.BottomNavigationPages.MyProfile;
import com.example.drugstry3.BottomNavigationPages.Settings;
import com.example.drugstry3.BottomNavigationPages.Shopping;
import com.example.drugstry3.LanguageChange.LocaleHelper;
import com.google.android.material.navigation.NavigationBarView;

public class doingStuff extends FragmentActivity{

    Context context;
    public static Resources resources;
    NavigationBarView bottomNavigationView;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_stuff);

        ///init
        changeLanguage("en");
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.myprofile:
                    selectedFragment = new MyProfile();
                    break;
                case R.id.invoices:
                    selectedFragment = new Invoices();
                    break;
                case R.id.shopping:
                    selectedFragment = new Shopping();
                    break;
                case R.id.settings:
                    selectedFragment = new Settings();
                    break;
                default:selectedFragment = new MyProfile();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MyProfile()).commit();

    }




    @Override
    public void onBackPressed() {
        if(Shopping.viewPager2.getCurrentItem() == 0){
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        }
        else{
            // Otherwise, select the previous step.
            Shopping.viewPager2.setCurrentItem(Shopping.viewPager2.getCurrentItem()-1);
        }
    }

    public void changeLanguage(String language){
        context = LocaleHelper.setLocale(doingStuff.this, language);
        resources = context.getResources();
    }

}