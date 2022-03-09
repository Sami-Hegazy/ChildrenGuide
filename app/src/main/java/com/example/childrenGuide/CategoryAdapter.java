package com.example.childrenGuide;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class CategoryAdapter extends FragmentStateAdapter {

    private final Context mContext;



    public CategoryAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context mContext) {
        super(fragmentManager, lifecycle);
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ColorsFragment();
        } else if (position == 1){
            return new FamilyFragment();
        } else if(position == 2){
            return new NumbersFragment();
        }else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}