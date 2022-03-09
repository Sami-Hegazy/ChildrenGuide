package com.example.childrenGuide;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager2 viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager() , getLifecycle(),this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = findViewById(R.id.tabs);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(getPageTitle(this,position))
        ).attach();

    }

    public String getPageTitle(Context mContext , int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_colors);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_numbers);
        } else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}