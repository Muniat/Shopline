package com.example.shopline.View;

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

import com.example.shopline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    Button signUpButton,continueButton;
    TextView loginTextView;
    EditText emailEditText,passwordEditText;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpButton = (Button) findViewById(R.id.loginButton);
        continueButton = (Button) findViewById(R.id.continueButton);
        loginTextView = (TextView) findViewById(R.id.signUpTextView);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        mAuth = FirebaseAuth.getInstance();



        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });




        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = emailEditText.getText().toString();
                String pass = passwordEditText.getText().toString();
                if(TextUtils.isEmpty(mail) && TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUpActivity.this, "Email Or password Empty", Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass)){
                    mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Invalid User Or Password", Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });




        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
