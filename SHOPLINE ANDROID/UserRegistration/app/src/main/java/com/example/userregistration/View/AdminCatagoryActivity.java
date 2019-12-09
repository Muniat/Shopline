package com.example.userregistration.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.userregistration.R;

public class AdminCatagoryActivity extends AppCompatActivity {
    ImageView Sports,Furnishers,Science,Vehicle,Art,Devices,Tools,Musical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_catagory);


        Sports = (ImageView) findViewById(R.id.Sports);
        Furnishers = (ImageView) findViewById(R.id.Furnishers);
        Science = (ImageView) findViewById(R.id.Science);
        Vehicle = (ImageView) findViewById(R.id.Vehicle);
        Art = (ImageView) findViewById(R.id.Art);
        Devices = (ImageView) findViewById(R.id.Devices);
        Tools = (ImageView) findViewById(R.id.Tools);
        Musical = (ImageView) findViewById(R.id.Musical);


        Sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Sports");
                startActivity(intent);
            }
        });



        Furnishers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Furnishers");
                startActivity(intent);
            }
        });




        Science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Science");
                startActivity(intent);
            }
        });



        Vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Vehicle");
                startActivity(intent);
            }
        });

        Art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Art");
                startActivity(intent);
            }
        });




        Devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Devices");
                startActivity(intent);
            }
        });



        Tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Tools");
                startActivity(intent);
            }
        });



        Musical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Catagory", "Musical");
                startActivity(intent);
            }
        });
    }
}
