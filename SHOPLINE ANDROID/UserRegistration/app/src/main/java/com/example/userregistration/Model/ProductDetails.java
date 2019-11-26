package com.example.userregistration.Model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.Adapters.ProductDetailsAdapter;
import com.example.userregistration.Fragments.AccountFragment;
import com.example.userregistration.Fragments.CartFragment;
import com.example.userregistration.Fragments.DescriptionFragment;
import com.example.userregistration.Fragments.HomeFragment;
import com.example.userregistration.HomeActivity;
import com.example.userregistration.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ProductDetails extends AppCompatActivity {
    ImageView detailsImageView;
    TextView detailsPrice, detailsProductaName,descriptionTextView;
    Context mcontext;
    Button addToCartButton;
    CartFragment cartFragment;
    HomeFragment homeFragment;
    AccountFragment accountFragment;
    ViewPager viewPager;
    TabLayout tabLayout;
    BottomNavigationView mbottomNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        detailsImageView = (ImageView) findViewById(R.id.detailsImageView);
        detailsPrice = (TextView) findViewById(R.id.detailsPrice);
        detailsProductaName = (TextView) findViewById(R.id.detailsProductaName);
        addToCartButton = (Button) findViewById(R.id.addToCartButton);
        //viewPager = (ViewPager) findViewById(R.id.viewPager);
        //tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);


        Intent intent = getIntent();
        final String name = intent.getExtras().getString("detailsProductaName");
        final String description = intent.getExtras().getString("porductDetails");
        final String price = intent.getExtras().getString("detailsPrice");
        final String Url = intent.getExtras().getString("detailsImageView");

        detailsPrice.setText("Price : " + price);
        detailsProductaName.setText(name);
        Picasso.with(mcontext).load(Url).fit().centerInside().into(detailsImageView);
        descriptionTextView.setText(description);

        /*viewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
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
            });    *******/

        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        accountFragment = new AccountFragment();
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homeNav :
                        Toast.makeText(ProductDetails.this, "Home Pressed", Toast.LENGTH_SHORT).show();

                        addFragment(homeFragment);
                        return true;
                    case R.id.accountNav:
                        Toast.makeText(ProductDetails.this, "Account", Toast.LENGTH_SHORT).show();
                        addFragment(accountFragment);
                        return true;
                    case R.id.cartNav:
                        Toast.makeText(ProductDetails.this, "cart", Toast.LENGTH_SHORT).show();
                        addFragment(cartFragment);
                        return  true;
                    default:
                        return false;
                }
            }
        });

    }
    public void AddProductToCart(View view){
        FragmentManager fm = getSupportFragmentManager();
        CartFragment cartFragment = new CartFragment();
        fm.beginTransaction().replace(R.id.productDetailsActivity,cartFragment).addToBackStack("").commit();

        detailsPrice.setVisibility(View.GONE);
        detailsProductaName.setVisibility(View.GONE);
        descriptionTextView.setVisibility(View.GONE);
        detailsImageView.setVisibility(View.GONE);
        addToCartButton.setVisibility(View.GONE);
    }
    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.productDetailsActivity,fragment);
        fragmentTransaction.commit();
    }



}


