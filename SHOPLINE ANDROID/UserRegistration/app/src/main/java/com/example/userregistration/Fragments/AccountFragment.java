package com.example.userregistration.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.R;
import com.example.userregistration.View.WelcomeActivity;
import com.example.userregistration.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    TextView userEmail,userPhone;
    Button logOutButton;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_account, container, false);
        userEmail = (TextView) rootview.findViewById(R.id.userEmail);
        userPhone = (TextView) rootview.findViewById(R.id.userPhone);
        logOutButton = (Button) rootview.findViewById(R.id.logOutButton);
        Paper.init(AccountFragment.this.getContext());
        setUserInfo();

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent = new Intent(AccountFragment.this.getContext(), WelcomeActivity.class);
                startActivity(intent);

            }
        });


        return rootview;
    }

    private void setUserInfo() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users")
                .child(Prevalent.currentOnlineUser.getPhone());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String mail = dataSnapshot.child("email").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    userEmail.setText("Email : " + mail);
                    userPhone.setText("Phone No. : " + phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
