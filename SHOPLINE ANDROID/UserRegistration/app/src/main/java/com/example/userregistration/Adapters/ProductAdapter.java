package com.example.userregistration.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userregistration.Model.Item;
import com.example.userregistration.R;

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
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class productViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mName;
        public TextView mPrice;

        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.productImageView);
            mName = itemView.findViewById(R.id.productNameTextView);
            mPrice = itemView.findViewById(R.id.productPriceTextView);

        }
    }


}
