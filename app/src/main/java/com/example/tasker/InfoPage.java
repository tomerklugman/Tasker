package com.example.tasker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InfoPage extends AppCompatActivity {

    TextView name,mail,pass,user;
    Button logout;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.info_page);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.password);
        user=findViewById(R.id.username);
        logout=findViewById(R.id.logout);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){

            database = FirebaseDatabase.getInstance();
            reference = database.getReference("users");

            String Name=account.getDisplayName();
            String Mail=account.getEmail();

            name.setText(Name);
            mail.setText(Mail);


            Toast.makeText(InfoPage.this,"logged in",Toast.LENGTH_SHORT).show();

        } else { // regular sign in

            name.setText(userGettersSetters.username);
            mail.setText(userGettersSetters.password);


        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainLoginPage.class));
                    }
                });
            }
        });
    }
    
}