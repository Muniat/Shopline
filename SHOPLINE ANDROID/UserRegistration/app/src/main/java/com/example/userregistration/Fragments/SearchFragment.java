package com.example.userregistration.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userregistration.Model.Item;
import com.example.userregistration.R;
import com.example.userregistration.View.ProductDetailsActivity;
import com.example.userregistration.ViewHolders.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    EditText searchEditText;
    Button searchButton;
    RecyclerView searchRecyclerView;
    private String inputText;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_search, container, false);

        searchEditText = (EditText)rootview.findViewById(R.id.searchEditText);
        searchButton = (Button) rootview.findViewById(R.id.searchButton);
        searchRecyclerView = (RecyclerView) rootview.findViewById(R.id.searchRecyclerView);

        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(SearchFragment.this.getContext(),2));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = searchEditText.getText().toString();
                onStart();
            }
        });

        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();

        DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference().child("Products");
        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(searchRef.orderByChild("pname").startAt(inputText),Item.class)
                .build();
        FirebaseRecyclerAdapter<Item, ProductViewHolder> adapter
                = new FirebaseRecyclerAdapter<Item, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull final Item item) {
                holder.productNameTextView.setText(item.getPname());
                holder.productPriceTextView.setText("Price : "+item.getPrice());
                Picasso.with(SearchFragment.this.getContext()).load(item.getImage()).fit().centerInside().into(holder.productImageView);
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
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        searchRecyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
