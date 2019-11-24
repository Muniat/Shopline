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
import com.example.userregistration.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    RecyclerView CartRecyclerView;
    ArrayList<CartItem> product;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_cart, container, false);

        CartRecyclerView = root.findViewById(R.id.CartRecyclerView);
        String name = "";
        String price = "";
        String Url = "";
        product.add(new CartItem(name, price ,Url));


        CartProductAdapter cartProductAdapter = new CartProductAdapter(CartFragment.this.getContext(),product);
        CartRecyclerView.setAdapter(cartProductAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        CartRecyclerView.setLayoutManager(layoutManager);


        return root;
    }

}
