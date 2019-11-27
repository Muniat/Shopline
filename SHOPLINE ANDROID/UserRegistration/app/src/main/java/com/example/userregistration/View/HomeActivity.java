package com.example.userregistration.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.Fragments.AccountFragment;
import com.example.userregistration.Fragments.CartFragment;
import com.example.userregistration.Fragments.HomeFragment;
import com.example.userregistration.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView logOutTextView;
    FrameLayout mframeLayout;
    BottomNavigationView mbottomNavigationView;
    HomeFragment homeFragment;
    AccountFragment accountFragment;
    CartFragment cartFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        accountFragment = new AccountFragment();
        addFragment(homeFragment);

        mframeLayout = (FrameLayout) findViewById(R.id.mainFrame);
        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);

        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homeNav :
                        Toast.makeText(HomeActivity.this, "Home Pressed", Toast.LENGTH_SHORT).show();
                        addFragment(homeFragment);
                        return true;
                    case R.id.accountNav:
                        Toast.makeText(HomeActivity.this, "Account", Toast.LENGTH_SHORT).show();
                        addFragment(accountFragment);
                        return true;
                    case R.id.cartNav:
                        Toast.makeText(HomeActivity.this, "cart", Toast.LENGTH_SHORT).show();
                        addFragment(cartFragment);
                        return  true;
                        default:
                            return false;
                }
            }
        });






       /* logOutTextView = (TextView) findViewById(R.id.logOutTextView);

        logOutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }); */
    }

    private void addFragment(Fragment fragment) {
        if(fragment == cartFragment){
            Bundle b = new Bundle();
            b.clear();
            fragment.setArguments(b);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame,fragment);
        fragmentTransaction.commit();
    }
}
