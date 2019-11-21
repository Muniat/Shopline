package com.example.userregistration.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userregistration.R;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ProductDetails extends AppCompatActivity {
    ImageView detailsImageView;
    TextView detailsPrice, porductDetailsTextView, detailsProductaName;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        detailsImageView = (ImageView) findViewById(R.id.detailsImageView);
        detailsPrice = (TextView) findViewById(R.id.detailsPrice);
        detailsProductaName = (TextView) findViewById(R.id.detailsProductaName);
        porductDetailsTextView = (TextView) findViewById(R.id.porductDetailsTextView);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("detailsProductaName");
        String description = intent.getExtras().getString("porductDetails");
        String price = intent.getExtras().getString("detailsPrice");
        String Url = intent.getExtras().getString("detailsImageView");

        detailsPrice.setText("Price : " + price);
        detailsProductaName.setText(name);
        porductDetailsTextView.setText(description);
        Picasso.with(mcontext).load(Url).fit().centerInside().into(detailsImageView);


    }
}


