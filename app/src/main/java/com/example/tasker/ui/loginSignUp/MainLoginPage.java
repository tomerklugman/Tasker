package com.example.tasker.ui.loginSignUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasker.R;
import com.example.tasker.models.userGettersSetters;
import com.example.tasker.ui.home_bottom_toolbar_child;
import com.example.tasker.ui.home_bottom_toolbar_parent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainLoginPage extends AppCompatActivity {
    //---------------------------------------------google auth

    ImageView google_img;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //---------------------------------------------google auth

    //---------------------------------------------regular user auth

    EditText signupPassword, signupUsername,signupStatus;
    Button signupButton;
    TextView loginRedirectText;

    //---------------------------------------------regular user auth

    //---------------------------------------------FirebaseAuth auth;

    FirebaseDatabase database;
    DatabaseReference reference;


    //---------------------------------------------FirebaseAuth auth;

    //---------------------------------------------drop down menu

    String[] item = {"parent","child"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    //---------------------------------------------drop down menu


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_page);

        //---------------------------------------------drop down menu

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainLoginPage.this,"Chose "+ item,Toast.LENGTH_SHORT).show();

            }
        });

        //---------------------------------------------drop down menu

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
        signupStatus = findViewById(R.id.auto_complete_txt);


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


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username");

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        userGettersSetters.house=null;
                        if (!user.isEmpty() && !pass.isEmpty() && !status.isEmpty()) {

                            if (snapshot.exists()) {
                                signupUsername.setError(null);
                                String userFromDB = snapshot.child(user).child("username").getValue(String.class);

                                if (!Objects.equals(userFromDB, user)) {
                                    userGettersSetters userGettersSetters = new userGettersSetters(user, pass, status);
                                    reference.child(user).setValue(userGettersSetters);

                                    Toast.makeText(MainLoginPage.this, "you have signed up succesfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainLoginPage.this, login_regular.class);
                                    startActivity(intent);

                                } else {

                                    signupUsername.setError(null);
                                    signupUsername.setError("username in use");
                                    signupUsername.requestFocus();

                                }
                            }


                        }else {
                            signupUsername.setError(null);
                            signupUsername.setError("username or password or status cant be empty");
                            signupUsername.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


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


                String user = task.getResult().getDisplayName();
                String pass = task.getResult().getEmail();



                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username");

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        System.out.println(user);
                        String statusFromDB = snapshot.child(user).child("status").getValue(String.class);
                        String houseFromDB = snapshot.child(user).child("house").getValue(String.class);

                        System.out.println(statusFromDB);

                        if (Objects.equals(statusFromDB,null)) {
                            userGettersSetters userGettersSetters = new userGettersSetters(user,pass);
                            reference.child(user).setValue(userGettersSetters);

                           // task.getResult(ApiException.class);
                            finish();
                            Intent intent = new Intent(getApplicationContext(), GoogleChildParentPick.class);
                            startActivity(intent);
                        } else {
                            userGettersSetters.house=houseFromDB;
                            if (Objects.equals(statusFromDB, "parent")) {

                                Intent intent = new Intent(getApplicationContext(), home_bottom_toolbar_parent.class);
                                startActivity(intent);

                            } else {
                                Intent intent = new Intent(getApplicationContext(), home_bottom_toolbar_child.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });











        }
//---------------------------------------------google auth

    }
}