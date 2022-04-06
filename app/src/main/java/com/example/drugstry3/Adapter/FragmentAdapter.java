package com.example.drugstry3.Adapter;

import static com.example.drugstry3.doingStuff.NUM_PAGES;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.drugstry3.Pages.Companies;
import com.example.drugstry3.Pages.Products;
import com.example.drugstry3.Pages.Repositories;
import com.example.drugstry3.Pages.Types;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new Repositories();
        }
        else if(position == 1){
            return new Companies();
        }
        else if(position == 2){
            return new Types();
        }
        else if(position == 3){
            return new Products();
        }
        return new Repositories();
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

}
