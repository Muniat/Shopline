package com.example.userregistration.Fragments;


import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.userregistration.Adapters.CartProductAdapter;
import com.example.userregistration.Model.CartItem;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.example.userregistration.ViewHolders.CartViewHolder;
import com.example.userregistration.prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    Button continueTocheckoutButton;
    TextView totalTextView;

    public CartFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_cart, container, false);
        CartRecyclerView =(RecyclerView) rootview.findViewById(R.id.CartRecyclerView);
        continueTocheckoutButton = (Button) rootview.findViewById(R.id.continueTocheckoutButton);
        totalTextView = (TextView) rootview.findViewById(R.id.totalTextView);


        CartRecyclerView.setHasFixedSize(true);
        CartRecyclerView.setLayoutManager(new LinearLayoutManager(CartFragment.this.getContext()));




        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();
        final DatabaseReference cartListReferrence = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<CartItem> options = new FirebaseRecyclerOptions.Builder<CartItem>()
                .setQuery(cartListReferrence.child("User View")
                        .child(Prevalent.currentOnlineUser.getPhone())
                        .child("Products"),CartItem.class).build();

        FirebaseRecyclerAdapter<CartItem, CartViewHolder> adapter =
                new FirebaseRecyclerAdapter<CartItem, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull CartItem cartItem) {
                        cartViewHolder.mCartName.setText(cartItem.getProductName());
                        cartViewHolder.mCartPrice.setText(cartItem.getProductPrice());
                        cartViewHolder.mCartQuantity.setText(cartItem.getQuantity());

                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_list,parent,false);
                        CartViewHolder holder = new CartViewHolder(view);
                        return holder;
                    }
                };
        CartRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
