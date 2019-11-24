package com.example.userregistration.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userregistration.Model.CartItem;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder> {
    private Context mcontext;
    private ArrayList<CartItem> mProductList;

    public CartProductAdapter(Context mcontext, ArrayList<CartItem> mProductList) {
        this.mcontext = mcontext;
        this.mProductList = mProductList;
    }

    @NonNull
    @Override
    public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.cart_product_list,parent,false);
        return new CartProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductViewHolder holder, int position) {






        CartItem currentItem = mProductList.get(position);
        String imageURL = currentItem.getmImageURL();
        String name = currentItem.getmName();
        String price = currentItem.getmPrice();
        holder.mCartName.setText(name);
        holder.mCartPrice.setText("Price : " + price);
        Picasso.with(mcontext).load(imageURL).fit().centerInside().into(holder.mCartImageView);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class CartProductViewHolder extends RecyclerView.ViewHolder {
        ImageView mCartImageView;
        TextView mCartName,mCartPrice;
        public CartProductViewHolder(@NonNull View itemView) {
            super(itemView);

            mCartImageView = itemView.findViewById(R.id.mCartImageView);
            mCartPrice = itemView.findViewById(R.id.mCartPrice);
            mCartName = itemView.findViewById(R.id.mCartName);
        }
    }
}
