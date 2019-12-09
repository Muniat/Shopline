package com.example.userregistration.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregistration.Model.User;
import com.example.userregistration.R;
import com.example.userregistration.prevalent.Prevalent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    GoogleSignInClient mGoogleSignInClient;
    EditText phoneEditText,passwordEditText;
    TextView infoTextView,signUpTextView,loginAsUser,loginAsAdmin;
    Button loginButton,googleButton;
    public ProgressDialog loadingBar;
    private String rootDb = "users";
    CheckBox rememberMeCheckbox;




    public void userLogin(){
        String password = passwordEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter A Password", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Enter A Phone No", Toast.LENGTH_SHORT).show();
        }else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please Wait");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            accountAccess(phone,password);
        }
    }

    private void accountAccess(String phone, String password) {

        if(rememberMeCheckbox.isChecked()){
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        DatabaseReference rootRef = getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(rootDb).child(phone).exists()){
                    User userData = dataSnapshot.child(rootDb).child(phone).getValue(User.class);
                    if(userData.getPhone().equals(phone)) {
                        if(userData.getPassword().equals(password)){
                            if(rootDb == "admins"){
                                Toast.makeText(LoginActivity.this, "Logged Into Account " + phone, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this, AdminCatagoryActivity.class);
                                startActivity(intent);
                            }else if(rootDb=="users"){
                                Toast.makeText(LoginActivity.this, "Logged Into Account " + phone, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "No record for this phone No.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this, "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginAsUser = (TextView) findViewById(R.id.loginAsUser);
        loginAsAdmin = (TextView) findViewById(R.id.loginAsAdmin);
        loadingBar = new ProgressDialog(this);
        googleButton = (Button) findViewById(R.id.loginGoogle);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        rememberMeCheckbox = (CheckBox) findViewById(R.id.rememberMeCheckbox);
        Paper.init(this);




        firebaseAuth = FirebaseAuth.getInstance();
        /*FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }*/


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser == null){
                    Toast.makeText(LoginActivity.this, "No Account Found", Toast.LENGTH_SHORT).show();
                }else if(firebaseUser != null){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                }
            }
        };

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });


        loginAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("User Login");
                loginAsUser.setVisibility(View.INVISIBLE);
                loginAsAdmin.setVisibility(View.VISIBLE);
                rootDb = "users";

            }
        });

        loginAsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login Admin");
                loginAsAdmin.setVisibility(View.INVISIBLE);
                loginAsUser.setVisibility(View.VISIBLE);
                rootDb = "admins";
                Toast.makeText(LoginActivity.this, rootDb, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }




    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }



}
