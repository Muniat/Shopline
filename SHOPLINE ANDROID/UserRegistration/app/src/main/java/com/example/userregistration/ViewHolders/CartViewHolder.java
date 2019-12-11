package com.example.userregistration.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userregistration.Interfaces.ItemClickListener;
import com.example.userregistration.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mCartName,mCartQuantity,mCartPrice;

    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        mCartName = itemView.findViewById(R.id.mCartName);
        mCartQuantity = itemView.findViewById(R.id.mCartQuantity);
        mCartPrice = itemView.findViewById(R.id.mCartPrice);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
