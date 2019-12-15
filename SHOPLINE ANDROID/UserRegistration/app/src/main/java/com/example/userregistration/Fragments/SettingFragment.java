package com.example.userregistration.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.R;
import com.example.userregistration.View.HomeActivity;
import com.example.userregistration.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    ImageView profilePicture;
    TextView changeImageTextview,updateSettings,closeSettings;
    EditText changeNumber,changeEmail;
    public ProgressDialog loadingBar;


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
        changeEmail = (EditText) rootview.findViewById(R.id.changeEmail);
        
        setUserInfo();

        updateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Login Account");
                loadingBar.setMessage("Please Wait");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                String mail = changeEmail.getText().toString();
                String phone = changeNumber.getText().toString();
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("users").child(Prevalent.currentOnlineUser.getPhone());
                updateRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String snapMail = dataSnapshot.child("email").getValue().toString();
                        String snapPhone = dataSnapshot.child("phone").getValue().toString();
                        String snapPass = dataSnapshot.child("password").getValue().toString();
                        if(mail == snapMail && phone== snapPhone){
                            Toast.makeText(SettingFragment.this.getContext(), "No change in email or password", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }else{
                            HashMap<Object, String> updateMap = new HashMap<>();
                            updateMap.put("email", mail);
                            updateMap.put("phone", phone);
                            updateMap.put("password", snapPass);
                            updateRef.setValue(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SettingFragment.this.getContext(), "Account Information Updated", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }else{
                                        Toast.makeText(SettingFragment.this.getContext(), "Update Failed ! ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingFragment.this.getContext(), HomeActivity.class);
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
                    changeEmail.setText(mail);
                    changeNumber.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
