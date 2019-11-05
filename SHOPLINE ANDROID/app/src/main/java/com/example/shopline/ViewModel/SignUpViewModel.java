package com.example.shopline.ViewModel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.shopline.View.LoginActivity;
import com.example.shopline.View.SignUpActivity;

public class SignUpViewModel extends ViewModel {
    public void onLoginTextViewPressed(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

    }
}
