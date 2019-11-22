package com.example.userregistration.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.Fragments.CartFragment;
import com.example.userregistration.R;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ProductDetails extends AppCompatActivity {
    ImageView detailsImageView;
    TextView detailsPrice, porductDetailsTextView, detailsProductaName;
    Context mcontext;
    Button addToCartButton;
    CartFragment cartFragment;


    public void onAddTOCart(View view){
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        detailsImageView = (ImageView) findViewById(R.id.detailsImageView);
        detailsPrice = (TextView) findViewById(R.id.detailsPrice);
        detailsProductaName = (TextView) findViewById(R.id.detailsProductaName);
        porductDetailsTextView = (TextView) findViewById(R.id.porductDetailsTextView);
        addToCartButton = (Button) findViewById(R.id.addToCartButton);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("detailsProductaName");
        String description = intent.getExtras().getString("porductDetails");
        final String price = intent.getExtras().getString("detailsPrice");
        final String Url = intent.getExtras().getString("detailsImageView");

        detailsPrice.setText("Price : " + price);
        detailsProductaName.setText(name);
        porductDetailsTextView.setText(description);
        Picasso.with(mcontext).load(Url).fit().centerInside().into(detailsImageView);





    }
}


