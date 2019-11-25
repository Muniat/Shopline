package com.example.userregistration.Adapters;

import android.content.Intent;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.userregistration.Fragments.DescriptionFragment;
import com.example.userregistration.R;

public class ProductDetailsAdapter extends FragmentPagerAdapter {
    int totalTabs;
    Intent intent;
    public ProductDetailsAdapter(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                return descriptionFragment;
            case 1:
                DescriptionFragment specificationFragment = new DescriptionFragment();
                return specificationFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
