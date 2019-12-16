package com.example.userregistration.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.example.userregistration.View.ArCoreActivity;
import com.example.userregistration.View.ProductDetailsActivity;
import com.example.userregistration.ViewHolders.ProductViewHolder;
import com.example.userregistration.prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerview;
    private RequestQueue mRequestQueue;
    CardView cardView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Item> items;
    Button augmentedButton;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerview = rootview.findViewById(R.id.recyclerView);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        items = new ArrayList<Item>();
        augmentedButton = (Button) rootview.findViewById(R.id.augmentedButton);


        //mRequestQueue = Volley.newRequestQueue(this.getContext());
        //parseJson();




        databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Item item = dataSnapshot1.getValue(Item.class);
                    items.add(item);
                }
                mProductAdapter = new ProductAdapter(HomeFragment.this.getContext(),items);
                mRecyclerview.setAdapter(mProductAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        return rootview;
    }





    /*private void parseJson() {
        String url = "https://api.myjson.com/bins/vvjiu";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("products");
                            ArrayList<String> descriptions = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String creatorName = hit.getString("name");
                                String imageURl = hit.getString("image");
                                String price = hit.getString("price");
                                String description = hit.getString("description");
                                descriptions.add(description);
                                //items.add(new Item(imageURl, creatorName, price, description));
                            }
                            //mProductAdapter = new ProductAdapter(HomeFragment.this.getContext(), items);
                            //mRecyclerview.setAdapter(mProductAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }*/

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(databaseReference, Item.class)
                .build();

        FirebaseRecyclerAdapter<Item, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Item, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull Item item) {
                holder.productNameTextView.setText(item.getPname());
                holder.productPriceTextView.setText("Price : "+item.getPrice());
                Picasso.with(HomeFragment.this.getContext()).load(item.getImage()).fit().centerInside().into(holder.productImageView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                        intent.putExtra("pid",item.getPid());
                        intent.putExtra("price",item.getPrice());
                        intent.putExtra("description",item.getDescription());
                        startActivity(intent);
                    }
                });
                holder.augmentedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = item.getPname();
                        Intent intent = new Intent(getActivity(), ArCoreActivity.class);
                        intent.putExtra("pname", name);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
                 ProductViewHolder holder = new ProductViewHolder(view);
                 return holder;
            }
        };
        mRecyclerview.setAdapter(adapter);
        adapter.startListening();


    }





}
