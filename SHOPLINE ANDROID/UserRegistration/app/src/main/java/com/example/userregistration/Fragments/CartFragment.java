package com.example.userregistration.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userregistration.Adapters.CartProductAdapter;
import com.example.userregistration.Model.CartItem;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    RecyclerView CartRecyclerView;
    CartProductAdapter cartProductAdapter;
    ArrayList<CartItem> cartItems;
    DatabaseReference cartListReferrence;

    public CartFragment(){

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


        cartListReferrence = FirebaseDatabase.getInstance().getReference().child("Cart List").child("10");
        cartListReferrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    CartItem item = dataSnapshot.getValue(CartItem.class);
                    cartItems.add(item);
                }
                cartProductAdapter = new CartProductAdapter(CartFragment.this.getContext(),cartItems);
                CartRecyclerView.setAdapter(cartProductAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return rootview;
    }

}
