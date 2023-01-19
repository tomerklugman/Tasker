package com.example.tasker.loginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasker.R;
import com.example.tasker.models.userGettersSetters;
import com.example.tasker.ui.home_bottom_toolbar_child;
import com.example.tasker.ui.home_bottom_toolbar_parent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class GoogleChildParentPick extends AppCompatActivity {



    String[] item = {"parent","child"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    EditText signupStatus;
    Button pickstatusButton;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_child_parent_pick);


        autoCompleteTextView = findViewById(R.id.picktxt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(GoogleChildParentPick.this,"Chose "+ item,Toast.LENGTH_SHORT).show();

            }
        });

        signupStatus = findViewById(R.id.picktxt);

        pickstatusButton = findViewById(R.id.pickstatusbutton);

        pickstatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String status = signupStatus.getText().toString().trim();


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username");

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String userFromDB = snapshot.child(userGettersSetters.username).child("username").getValue(String.class);
                        String houseFromDB = snapshot.child(userGettersSetters.username).child("house").getValue(String.class);

                        reference.child(userFromDB).child("status").setValue(status);

                        if (Objects.equals(houseFromDB,null) ) { // user not in house

                            if (Objects.equals(status, "parent")) {

                                Intent intent = new Intent(GoogleChildParentPick.this, Parent_After_Login_CreateJoinHouse.class);
                                startActivity(intent);

                            } else {

                                Intent intent = new Intent(GoogleChildParentPick.this, Child_After_login_JoinHouse.class);
                                startActivity(intent);

                            }

                        } else { // user in house

                            if (Objects.equals(status, "parent")) {

                                Intent intent = new Intent(GoogleChildParentPick.this, home_bottom_toolbar_parent.class);
                                startActivity(intent);

                            } else {
                                Intent intent = new Intent(GoogleChildParentPick.this, home_bottom_toolbar_child.class);
                                startActivity(intent);
                            }



                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}