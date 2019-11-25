package com.example.userregistration.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.Adapters.ProductDetailsAdapter;
import com.example.userregistration.Fragments.CartFragment;
import com.example.userregistration.Fragments.DescriptionFragment;
import com.example.userregistration.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ProductDetails extends AppCompatActivity {
    ImageView detailsImageView;
    TextView detailsPrice, detailsProductaName;
    Context mcontext;
    Button addToCartButton;
    CartFragment cartFragment;
    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        detailsImageView = (ImageView) findViewById(R.id.detailsImageView);
        detailsPrice = (TextView) findViewById(R.id.detailsPrice);
        detailsProductaName = (TextView) findViewById(R.id.detailsProductaName);
        addToCartButton = (Button) findViewById(R.id.addToCartButton);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("detailsProductaName");
        final String description = intent.getExtras().getString("porductDetails");
        final String price = intent.getExtras().getString("detailsPrice");
        final String Url = intent.getExtras().getString("detailsImageView");

        detailsPrice.setText("Price : " + price);
        detailsProductaName.setText(name);
        Picasso.with(mcontext).load(Url).fit().centerInside().into(detailsImageView);

        Log.i("tag", description);

        viewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });




    }
}


