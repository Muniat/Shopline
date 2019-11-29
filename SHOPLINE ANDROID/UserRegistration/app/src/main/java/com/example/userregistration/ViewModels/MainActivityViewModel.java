package com.example.userregistration.ViewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userregistration.Model.User;
import com.example.userregistration.View.HomeActivity;
import com.example.userregistration.View.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityViewModel extends ViewModel{
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> email = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();
    MutableLiveData<String> address = new MutableLiveData<>();
    MutableLiveData<String> mobileNo = new MutableLiveData<>();
    public User user;
    public Context context;

    public MainActivityViewModel(User user, Context context) {
        this.user = user;
        this.context = context;
    }
    public void onSignUpButtonClick(){
        user.setEmail(email.getValue());
        user.setPassword(password.getValue());
        user.setName(name.getValue());
        user.setAddress(address.getValue());
        user.setMobileNo(mobileNo.getValue());
        Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
    }


}
