package com.example.userregistration.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userregistration.Adapters.CartProductAdapter;
import com.example.userregistration.Model.CartItem;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    RecyclerView CartRecyclerView;
    CartProductAdapter cartProductAdapter;
    List<CartItem> cartItems;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_cart, container, false);
        CartRecyclerView =(RecyclerView) rootview.findViewById(R.id.CartRecyclerView);


        cartItems = new ArrayList<>();
        CartRecyclerView.setHasFixedSize(true);
        CartRecyclerView.setLayoutManager(new LinearLayoutManager(CartFragment.this.getContext()));
        cartItems.add(new CartItem("https://firebasestorage.googleapis.com/v0/b/registration-c3373.appspot.com/o/productImage%2FAcousticGuitar.PNG?alt=media&token=4e3537d5-4956-43cd-a9fe-a9ee65045b61","Guitar","5000"));
        cartProductAdapter = new CartProductAdapter(CartFragment.this.getContext(), (ArrayList<CartItem>) cartItems);
        CartRecyclerView.setAdapter(cartProductAdapter);
        return rootview;
    }

}
