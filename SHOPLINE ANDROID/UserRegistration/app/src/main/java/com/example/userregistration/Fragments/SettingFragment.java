package com.example.userregistration.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userregistration.R;
import com.example.userregistration.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    ImageView profilePicture;
    TextView changeImageTextview,updateSettings,closeSettings;
    EditText changeNumber,fullName,fullAddress,changeEmail;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_setting, container, false);
        profilePicture = (ImageView) rootview.findViewById(R.id.profilePicture);
        changeImageTextview = (TextView) rootview.findViewById(R.id.changeImageTextview);
        updateSettings = (TextView) rootview.findViewById(R.id.updateSettings);
        closeSettings = (TextView) rootview.findViewById(R.id.closeSettings);
        changeNumber = (EditText) rootview.findViewById(R.id.changeNumber);
        fullName = (EditText) rootview.findViewById(R.id.fullName);
        fullAddress = (EditText) rootview.findViewById(R.id.fullAddress);
        changeEmail = (EditText) rootview.findViewById(R.id.changeEmail);
        



        return rootview;
    }

}
