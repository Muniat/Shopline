package com.example.userregistration.Controller;

import androidx.annotation.NonNull;

import com.example.userregistration.Model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseController {
    FirebaseDatabase mDatabase;
    DatabaseReference mReferenceProducts;
    List<Item> products = new ArrayList<>();

    public interface DataStatus{
        public void dataIsLoaded(List<Item> items, List<String> keys);
        public void dataIsInserted();
        public void dataIsUpdated();
        public void dataIsDeleted();
    }

    public FirebaseDatabaseController() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceProducts = mDatabase.getReference("products");
    }

    public void fetchProducts(final DataStatus dataStatus){
        mReferenceProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Item item = keyNode.getValue(Item.class);
                    products.add(item);

                }
                dataStatus.dataIsLoaded(products,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
