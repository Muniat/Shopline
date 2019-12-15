package com.example.userregistration.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userregistration.Interfaces.ItemClickListener;
import com.example.userregistration.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView productNameTextView,productPriceTextView;
    public ImageView productImageView;
    public Button augmentedButton;
    public ItemClickListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productNameTextView = (TextView) itemView.findViewById(R.id.productNameTextView);
        productPriceTextView = (TextView) itemView.findViewById(R.id.productPriceTextView);
        productImageView = (ImageView) itemView.findViewById(R.id.productImageView);
        augmentedButton = (Button) itemView.findViewById(R.id.augmentedButton);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
