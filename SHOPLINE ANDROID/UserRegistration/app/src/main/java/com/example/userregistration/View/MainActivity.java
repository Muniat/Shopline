package com.example.userregistration.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.Model.User;
import com.example.userregistration.R;
import com.example.userregistration.ViewModels.MainActivityViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText,nameEditText,addressEditText,phoneEditText;
    Button signUpButton,continueButton;
    TextView infoTextView,loginMasgTextView;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;


    public void onSignUpButtonClick(View view){
        addUserRecord();
    }


    private void addUserRecord(){
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(address)){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Invalid User Or Password", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Your Account Has Been Created!", Toast.LENGTH_SHORT).show();
                        String id = firebaseDatabase.push().getKey();
                        User user = new User(id,name,email,password,address,phone);
                        firebaseDatabase.child(id).setValue(user);
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });


        }else{
            Toast.makeText(this, "Please Fill Up The Form Properly", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        signUpButton = (Button) findViewById(R.id.loginButton);
        loginMasgTextView = (TextView) findViewById(R.id.loginMasgTextView);
        continueButton = (Button) findViewById(R.id.continueButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("users");


        loginMasgTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
