package com.example.tasker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login_regular extends AppCompatActivity {



    EditText loginUsername, loginPassword;
    TextView signupRedirectText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_regular);


        loginUsername = findViewById(R.id.signin_regular_username);
        loginPassword = findViewById(R.id.signin_regular_password);
        loginButton = findViewById(R.id.signin_regular_button);
        signupRedirectText = findViewById(R.id.signin_regular_signUP);

        loginButton.setOnClickListener(new View.OnClickListener() { // button action
            @Override
            public void onClick(View view) {

                if (!validateUsername() | !validatePassword()) {

                } else {
                    checkUser();
                }
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_regular.this, MainLoginPage.class));
            }

        });
    }


    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("username cant be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("password cant be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        userGettersSetters.username=userUsername; // user login in constructor
        userGettersSetters.password=userPassword;


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userPassword).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB, userPassword)) {
                        loginUsername.setError(null);
                        Intent intent = new Intent(login_regular.this, InfoPage.class);
                        startActivity(intent);
                        Toast.makeText(login_regular.this,"logged in",Toast.LENGTH_SHORT).show();
                    } else {
                        loginPassword.setError("invalid input");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginUsername.setError("user does not exist");
                    loginUsername.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

