package com.example.userregistration.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userregistration.Model.Item;
import com.example.userregistration.Model.ProductDetails;
import com.example.userregistration.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter <ProductAdapter.productViewHolder>{
    private Context mcontext;
    private ArrayList<Item> mProductList;


    public ProductAdapter(Context mcontext, ArrayList<Item> mProductList) {
        this.mcontext = mcontext;
        this.mProductList = mProductList;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.product_list,parent,false);
        return new productViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final productViewHolder holder, int position) {
        Item currentItem = mProductList.get(position);
        String imageURL = currentItem.getmImageURL();
        String name = currentItem.getmName();
        String price = currentItem.getmPrice();
        holder.mName.setText(name);
        holder.mPrice.setText("Price : " + price);
        Picasso.with(mcontext).load(imageURL).fit().centerInside().into(holder.mImageView);


        //on cardVIew click listener
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent(mcontext, ProductDetails.class);
                 mcontext.startActivity(myIntent);


             }
         });

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class productViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mName;
        public TextView mPrice;
        CardView cardView;

        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.productImageView);
            mName = itemView.findViewById(R.id.productNameTextView);
            mPrice = itemView.findViewById(R.id.productPriceTextView);
            cardView = (CardView) itemView.findViewById(R.id.productCard);

        }
    }


}
