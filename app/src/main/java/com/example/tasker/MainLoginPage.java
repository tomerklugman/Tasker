package com.example.tasker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainLoginPage extends AppCompatActivity {
    //---------------------------------------------google auth

    ImageView google_img;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //---------------------------------------------google auth

    //---------------------------------------------regular user auth

    EditText signupPassword, signupUsername,signupStatus,signupHouse;
    Button signupButton;
    TextView loginRedirectText;

    //---------------------------------------------regular user auth

    //---------------------------------------------FirebaseAuth auth;

    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference reference1;


    //---------------------------------------------FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_page);

        //---------------------------------------------google auth start
        google_img = findViewById(R.id.google_sign_in);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        google_img.setOnClickListener(new View.OnClickListener() { // google sign in button
            @Override
            public void onClick(View view) {

                Intent intent = gsc.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });


        //---------------------------------------------google auth end

        //---------------------------------------------regular user auth start

        signupUsername = findViewById(R.id.signup_regular_username);
        signupPassword = findViewById(R.id.signup_regular_password);
        signupStatus = findViewById(R.id.signup_regular_status);
        signupHouse = findViewById(R.id.signup_regular_house);

        signupButton = findViewById(R.id.signup_regular_button);
        loginRedirectText = findViewById(R.id.signup_regular_signIN);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");


                String user = signupUsername.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String status = signupStatus.getText().toString().trim();
                String house = signupHouse.getText().toString().trim();

                userGettersSetters userGettersSetters = new userGettersSetters(user, pass, status,house);
                reference.child(user).setValue(userGettersSetters);

                if(Objects.equals(status, "parent")){
                    reference1 = database.getReference("houses");
                    reference1.child(house).child("1").setValue("");
                }

                Toast.makeText(MainLoginPage.this, "you have signed up succesfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainLoginPage.this, login_regular.class);
                startActivity(intent);

            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainLoginPage.this, login_regular.class));
            }
        });

        //---------------------------------------------regular user auth


    }

    //---------------------------------------------google auth
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == 100) {
            Task<GoogleSignInAccount> task;
            task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");


                String user = task.getResult().getDisplayName();
                String pass = task.getResult().getEmail();

                userGettersSetters userGettersSetters = new userGettersSetters(user,pass);
                reference.child(user).setValue(userGettersSetters);

                task.getResult(ApiException.class);
                finish();
                Intent intent = new Intent(getApplicationContext(), InfoPage.class);
                startActivity(intent);

            } catch (ApiException e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        }
//---------------------------------------------google auth

    }
}
