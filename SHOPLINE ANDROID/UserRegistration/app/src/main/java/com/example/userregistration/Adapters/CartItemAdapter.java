package com.example.userregistration.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>{
    private Context mcontext;
    private ArrayList<Item> mProductList;

    public CartItemAdapter(Context mcontext, ArrayList<Item> mProductList) {
        this.mcontext = mcontext;
        this.mProductList = mProductList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.cart_product_list,parent,false);
        return new CartItemAdapter.CartItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Item currentItem = mProductList.get(position);
        String imageURL = currentItem.getmImageURL();
        String price = currentItem.getmPrice();
        holder.mPrice.setText("Price : " + price);
        Picasso.with(mcontext).load(imageURL).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }




    public class CartItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mName;
        public TextView mPrice;
        //CardView cardView;
        //Button augmentedButton;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.productImageView);
            mName = itemView.findViewById(R.id.productNameTextView);
            mPrice = itemView.findViewById(R.id.productPriceTextView);

        }
    }
}
