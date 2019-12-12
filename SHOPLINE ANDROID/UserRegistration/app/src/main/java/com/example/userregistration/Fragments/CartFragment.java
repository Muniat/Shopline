package com.example.userregistration.Fragments;


import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.userregistration.Adapters.CartProductAdapter;
import com.example.userregistration.Model.CartItem;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.example.userregistration.View.FinalShipmentActivity;
import com.example.userregistration.View.ProductDetailsActivity;
import com.example.userregistration.ViewHolders.CartViewHolder;
import com.example.userregistration.prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    int finalTotalPrice=0;


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

        continueTocheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartFragment.this.getContext(), FinalShipmentActivity.class);
                intent.putExtra("total", String.valueOf(finalTotalPrice));
                startActivity(intent);
            }
        });



        final DatabaseReference cartListReferrence = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<CartItem> options = new FirebaseRecyclerOptions.Builder<CartItem>()
                .setQuery(cartListReferrence.child("User View")
                        .child(Prevalent.currentOnlineUser.getPhone())
                        .child("Products"),CartItem.class).build();

        FirebaseRecyclerAdapter<CartItem, CartViewHolder> adapter =
                new FirebaseRecyclerAdapter<CartItem, CartViewHolder>(options) {
                    int totalPrice = 0;
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int i, @NonNull CartItem cartItem) {
                        holder.mCartName.setText(cartItem.getProductName());
                        holder.mCartPrice.setText(cartItem.getProductPrice());
                        holder.mCartQuantity.setText(cartItem.getQuantity());
                        String price = cartItem.getProductPrice();
                        price = price.replaceAll("[^\\d.]", "");
                        String quantity = cartItem.getQuantity();
                        Toast.makeText(CartFragment.this.getContext(), price + "  " + quantity, Toast.LENGTH_SHORT).show();
                        int unitPrice = Integer.parseInt(price) * Integer.parseInt(quantity);
                        totalPrice  = totalPrice +  unitPrice;
                        totalTextView.setText("Total : " + Integer.toString(totalPrice));
                        finalTotalPrice = totalPrice;

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CharSequence options[] = new CharSequence[]{
                                        "Remove",
                                        "Edit"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(CartFragment.this.getContext());
                                builder.setTitle("Edit Or Remove Products");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(which == 0){
                                            cartListReferrence.child("User View").child(Prevalent.currentOnlineUser.getPhone())
                                                    .child("Products")
                                                    .child(cartItem.getPid())
                                                    .removeValue()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                Toast.makeText(CartFragment.this.getContext(), "Product Deleted From your Cart", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });

                                            cartListReferrence.child("Admin View").child(Prevalent.currentOnlineUser.getPhone())
                                                    .child("Products")
                                                    .child(cartItem.getPid())
                                                    .removeValue()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                Toast.makeText(CartFragment.this.getContext(), "Product Deleted From your Cart", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        }else if(which == 1 ){
                                            Intent intent = new Intent(CartFragment.this.getContext(), ProductDetailsActivity.class);
                                            intent.putExtra("pid", cartItem.getPid());
                                            startActivity(intent);
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });

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


        return rootview;
    }


}
