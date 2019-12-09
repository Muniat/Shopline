package com.example.userregistration.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.userregistration.R;

public class AdminAddProductActivity extends AppCompatActivity {

    private String catagoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        catagoryName = getIntent().getExtras().get("Catagory").toString();
    }
}
