package com.example.userregistration.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.userregistration.Fragments.AccountFragment;
import com.example.userregistration.Fragments.CartFragment;
import com.example.userregistration.Fragments.HomeFragment;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.example.userregistration.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView detailsImageView;
    TextView detailsPrice, detailsProductaName,descriptionTextView,quantitiTextView;
    Context mcontext;
    Button addToCartButton;
    CartFragment cartFragment;
    FirebaseUser firebaseUser;
    HomeFragment homeFragment;
    AccountFragment accountFragment;
    ViewPager viewPager;
    TabLayout tabLayout;
    BottomNavigationView mbottomNavigationView;
    String pid;
    private ElegantNumberButton elegantNumberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        quantitiTextView = (TextView) findViewById(R.id.quantitiTextView);
        elegantNumberButton = (ElegantNumberButton) findViewById(R.id.elegentNumberButton);
        detailsImageView = (ImageView) findViewById(R.id.detailsImageView);
        detailsPrice = (TextView) findViewById(R.id.detailsPrice);
        detailsProductaName = (TextView) findViewById(R.id.detailsProductaName);
        addToCartButton = (Button) findViewById(R.id.addToCartButton);
        //viewPager = (ViewPager) findViewById(R.id.viewPager);
        //tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);


        Intent intent = getIntent();
        pid = intent.getExtras().getString("pid");
        getProductDetails(pid);


        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProductToCart();
            }
        });

        //description = intent.getExtras().getString("porductDetails");
       // price = intent.getExtras().getString("detailsPrice");
        //Url = intent.getExtras().getString("detailsImageView");

        /* adapter called but not working
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
            });    *******/



        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        accountFragment = new AccountFragment();
        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homeNav :
                        Toast.makeText(ProductDetailsActivity.this, "Home Pressed", Toast.LENGTH_SHORT).show();

                        addFragment(homeFragment);
                        return true;
                    case R.id.accountNav:
                        Toast.makeText(ProductDetailsActivity.this, "Account", Toast.LENGTH_SHORT).show();
                        addFragment(accountFragment);
                        return true;
                    case R.id.cartNav:
                        Toast.makeText(ProductDetailsActivity.this, "cart", Toast.LENGTH_SHORT).show();
                        addFragment(cartFragment);
                        return  true;
                    default:
                        return false;
                }
            }
        });

    }


    public void AddProductToCart(){
        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String ,Object> cartMap = new HashMap<>();
        cartMap.put("pid",pid);
        cartMap.put("productName",detailsProductaName.getText().toString());
        cartMap.put("productPrice",detailsPrice.getText().toString());
        cartMap.put("Date",saveCurrentDate);
        cartMap.put("Time",saveCurrentTime);
        cartMap.put("quantity",elegantNumberButton.getNumber());

        databaseReference.child("User View").child(Prevalent.currentOnlineUser.getPhone())
                .child("Products").child(pid)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            databaseReference.child("Admin View").child(Prevalent.currentOnlineUser.getPhone())
                                    .child("Products").child(pid)
                                    .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ProductDetailsActivity.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });




        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.productDetailsActivity,cartFragment).addToBackStack("").commit();

        detailsPrice.setVisibility(View.GONE);
        detailsProductaName.setVisibility(View.GONE);
        descriptionTextView.setVisibility(View.GONE);
        detailsImageView.setVisibility(View.GONE);
        addToCartButton.setVisibility(View.GONE);
        elegantNumberButton.setVisibility(View.GONE);
        quantitiTextView.setVisibility(View.GONE);
    }

    private void addFragment(Fragment fragment) {
        if(fragment == cartFragment){
            Bundle b = new Bundle();
            b.clear();
            fragment.setArguments(b);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.productDetailsActivity,fragment);
        fragmentTransaction.commit();
    }


    public void getProductDetails(String position){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Products").child(pid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Item item = dataSnapshot.getValue(Item.class);
                    detailsPrice.setText("Price : " + item.getPrice());
                    detailsProductaName.setText(item.getPname());
                    Picasso.with(mcontext).load(item.getImage()).fit().centerInside().into(detailsImageView);
                    descriptionTextView.setText(item.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}


