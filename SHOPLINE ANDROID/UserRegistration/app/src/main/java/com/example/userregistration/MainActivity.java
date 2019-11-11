package com.example.userregistration;

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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailEditText,passwordEditText;
    Button signUpButton,continueButton;
    TextView infoTextView,loginMasgTextView;
    FirebaseAuth firebaseAuth; // creating a firebase authentication

    public void onSignUpButtonClick(View view){
        String mail = emailEditText.getText().toString();
        String pass = passwordEditText.getText().toString();
        if(TextUtils.isEmpty(mail) && TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Email Or password Empty", Toast.LENGTH_SHORT).show();
        }else if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass)){
            firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signUpButton = (Button) findViewById(R.id.loginButton);
        loginMasgTextView = (TextView) findViewById(R.id.loginMasgTextView);
        continueButton = (Button) findViewById(R.id.continueButton);


        firebaseAuth = FirebaseAuth.getInstance();


        loginMasgTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });








    }
}
