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

    public static String f_url ="https://tasker-c6fa1-default-rtdb.firebaseio.com/";
    EditText loginUsername, loginPassword;
    TextView signupRedirectText;
    Button loginButton;
    static String val;
    static String houseFromDB;
    static String valpass;

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
        val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("username cant be empty");
            return false;
        }
        else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        valpass = loginPassword.getText().toString();
        if (valpass.isEmpty()) {
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
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    String statusFromDB = snapshot.child(userUsername).child("status").getValue(String.class);
                    houseFromDB = snapshot.child(userUsername).child("house").getValue(String.class);

                    if (Objects.equals(passwordFromDB, userPassword)){
                        loginUsername.setError(null);

                        if (houseFromDB==null) {  // if user not in house
                            if (Objects.equals(statusFromDB, "parent")) { // if user is parent
                                Intent intent = new Intent(login_regular.this, Parent_After_Login_CreateJoinHouse.class);
                                startActivity(intent);
                                Toast.makeText(login_regular.this, "logged in", Toast.LENGTH_SHORT).show();
                            } else { // else user is child
                                Intent intent = new Intent(login_regular.this, Child_After_login_JoinHouse.class);
                                startActivity(intent);
                                Toast.makeText(login_regular.this, "logged in", Toast.LENGTH_SHORT).show();
                            }
                        } else { // user already in house
                            Intent intent = new Intent(login_regular.this, home_bottom_toolbar.class);
                            startActivity(intent);
                            Toast.makeText(login_regular.this, "logged in", Toast.LENGTH_SHORT).show();
                        }
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