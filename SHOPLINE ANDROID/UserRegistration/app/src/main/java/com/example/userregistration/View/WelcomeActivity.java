package com.example.userregistration.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.userregistration.Model.User;
import com.example.userregistration.R;
import com.example.userregistration.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class WelcomeActivity extends AppCompatActivity {
    Button joinNowButton,alreadyLoginButton;
    private String rootDb = "users";
    CheckBox rememberMeCheckbox;
    public ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        rememberMeCheckbox = (CheckBox) findViewById(R.id.rememberMeCheckbox);
        Paper.init(this);
        loadingBar = new ProgressDialog(this);
        joinNowButton = (Button) findViewById(R.id.joinNowButton);
        alreadyLoginButton = (Button) findViewById(R.id.alreadyLoginButton);

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);
        if(UserPhoneKey != "" && UserPasswordKey != ""){
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)){
                AllowDirectAccess(UserPhoneKey,UserPasswordKey);
                loadingBar.setTitle("Login Account");
                loadingBar.setMessage("Please Wait");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }


        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



        alreadyLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AllowDirectAccess(final String userPhoneKey, final String userPasswordKey) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(rootDb).child(userPhoneKey).exists()){
                    User userData = dataSnapshot.child(rootDb).child(userPhoneKey).getValue(User.class);
                    if(userData.getPhone().equals(userPhoneKey)) {
                        if(userData.getPassword().equals(userPasswordKey)){
                            Toast.makeText(WelcomeActivity.this, "Logged Into Account " + userPhoneKey, Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(WelcomeActivity.this,HomeActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(WelcomeActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(WelcomeActivity.this, "No record for this phone No.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(WelcomeActivity.this, "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
