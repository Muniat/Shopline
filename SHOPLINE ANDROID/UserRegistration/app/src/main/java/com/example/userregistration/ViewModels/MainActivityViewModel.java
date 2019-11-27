package com.example.userregistration.ViewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userregistration.View.HomeActivity;
import com.example.userregistration.View.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityViewModel{
    FirebaseAuth firebaseAuth;
    Context context;
    String email;
    String pass;
    public MainActivityViewModel(String email, String pass){
        this.email = email;
        this.pass = pass;
    }

    public void CreateUser(){
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(context, "Invalid User Or Password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }

}
