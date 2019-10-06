package com.example.augmentedapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView textView;
    EditText emailEditText,passwordEditText;
    Button signUpButton;
    //signUp button on click function

    public void onSignUpButtonClick(View view){
        String mail = emailEditText.getText().toString();
        String pass = passwordEditText.getText().toString();
        if(TextUtils.isEmpty(mail) && TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Email Or password Empty", Toast.LENGTH_SHORT).show();
        }else if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass)){
            mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Invalid User Or Password", Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    }
                }
            });
        }else{
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }





    //login textView on CLick Function
    public void loginViewPressed(View view){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpButton = (Button) findViewById(R.id.loginButton);
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        textView = (TextView) findViewById(R.id.signUpTextView);

        mAuth = FirebaseAuth.getInstance();




    }
}
